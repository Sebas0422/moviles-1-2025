package com.example.proyectofinal.models

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class LoginResponse(
    val access_token: String
)

data class RegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val profile: UserProfile
)

