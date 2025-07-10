package com.example.proyectofinal.repositories

import com.example.proyectofinal.models.LoginRequest
import com.example.proyectofinal.models.LoginResponse
import com.example.proyectofinal.models.RegisterRequest

object AuthRepository {
    private val authApi = RetrofitRepository.getAuthApi()

    suspend fun login(request: LoginRequest): LoginResponse {
        return authApi.login(request)
    }

    suspend fun register(request: RegisterRequest): String {
        return authApi.register(request)
    }
}