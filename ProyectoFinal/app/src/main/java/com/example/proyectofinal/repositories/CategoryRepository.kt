package com.example.proyectofinal.repositories

import com.example.proyectofinal.models.CategoryList
import com.example.proyectofinal.models.WorkerList

object CategoryRepository {
    private val categoryApi = RetrofitRepository.getCategoryApi()

    suspend fun getCategoryList(): CategoryList{
        return categoryApi.getCategories()
    }

    suspend fun getWorkersByCategoryId(id: Int): WorkerList {
        return categoryApi.getWorkersByCategoryId(id)
    }
}