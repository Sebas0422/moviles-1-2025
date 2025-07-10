package com.example.proyectofinal.api

import com.example.proyectofinal.models.CategoryList
import com.example.proyectofinal.models.WorkerList
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {
    @GET("categories")
    suspend fun getCategories(): CategoryList

    @GET("categories/{id}/workers")
        suspend fun getWorkersByCategoryId(@Path("id") id: Int): WorkerList
}