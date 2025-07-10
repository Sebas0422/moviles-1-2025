package com.example.proyectofinal.api

import com.example.proyectofinal.models.Worker
import com.example.proyectofinal.models.WorkerList
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkerApi {
    @GET("workers/{id}")
    suspend fun getWorker(@Path("id") id: Int): Worker
}