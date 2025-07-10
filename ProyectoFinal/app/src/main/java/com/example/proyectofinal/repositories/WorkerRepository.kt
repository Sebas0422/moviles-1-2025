package com.example.proyectofinal.repositories

import com.example.proyectofinal.models.Worker
import com.example.proyectofinal.models.WorkerList

object WorkerRepository {
    private val workerApi = RetrofitRepository.getWorkerApi()

    suspend fun getWorker(workerId: Int) : Worker{
        return workerApi.getWorker(workerId)
    }
}