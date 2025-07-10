package com.example.proyectofinal.api

import com.example.proyectofinal.models.LoginRequest
import com.example.proyectofinal.models.LoginResponse
import com.example.proyectofinal.models.RegisterRequest
import com.example.proyectofinal.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("client/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("client/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}