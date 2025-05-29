package com.example.practico_3_4.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practico_3_4.Repositories.ScoreRepository
import com.example.practico_3_4.db.models.Score
import com.example.practico_3_4.ui.activities.ScoreListActivity
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private val _scoreList: MutableLiveData<List<Score>> = MutableLiveData(arrayListOf())
    val scoreList: MutableLiveData<List<Score>> = _scoreList
    fun saveScore(context: Context, newScore: Score) {
        viewModelScope.launch {
            ScoreRepository.insertScore(context, newScore)

        }
    }

    fun loadScores(activity: Context) {
        viewModelScope.launch {
            val scores = ScoreRepository.getAllScores(activity)
            _scoreList.postValue(scores)

        }
    }
}