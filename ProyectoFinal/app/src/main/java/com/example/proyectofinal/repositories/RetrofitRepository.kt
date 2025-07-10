package com.example.proyectofinal.repositories

import com.example.proyectofinal.AuthInterceptor
import com.example.proyectofinal.api.AuthApi
import com.example.proyectofinal.api.CategoryApi
import com.example.proyectofinal.api.WorkerApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitRepository {
    private const val BASE_URL = "http://trabajos.jmacboy.com/api/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val unauthenticatedRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val authenticatedRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getAuthApi(): AuthApi = unauthenticatedRetrofit.create(AuthApi::class.java)
    fun getCategoryApi(): CategoryApi = authenticatedRetrofit.create(CategoryApi::class.java)
    fun getWorkerApi() : WorkerApi = authenticatedRetrofit.create(WorkerApi::class.java)
}