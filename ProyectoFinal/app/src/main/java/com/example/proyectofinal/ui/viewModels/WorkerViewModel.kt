package com.example.proyectofinal.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.models.Worker
import com.example.proyectofinal.repositories.WorkerRepository
import kotlinx.coroutines.launch

class WorkerViewModel: ViewModel() {
    private val _worker = MutableLiveData<Worker>()
    val worker: LiveData<Worker>  = _worker

    fun loadWorker(workerId: Int){
        viewModelScope.launch {
            try {
                val workerResponse = WorkerRepository.getWorker(workerId)
                _worker.postValue(workerResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}