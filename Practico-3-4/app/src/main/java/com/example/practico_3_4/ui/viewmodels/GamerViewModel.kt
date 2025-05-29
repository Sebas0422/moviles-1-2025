package com.example.practico_3_4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GamerViewModel : ViewModel() {
    private val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    fun resetScore() {
        _score.value = 0
    }

    fun addScore(points: Int) {
        _score.value = (_score.value ?: 0) + points
    }
}