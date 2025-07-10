package com.example.proyectofinal.repositories

import com.example.proyectofinal.models.Worker

object WorkerRepository {
    private val workerApi = RetrofitRepository.getWorkerApi()

    suspend fun getWorker(workerId: Int) : Worker{
        return workerApi.getWorker(workerId)
    }
}