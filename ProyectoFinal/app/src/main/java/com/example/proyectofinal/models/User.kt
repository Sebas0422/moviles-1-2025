package com.example.proyectofinal.models

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val profile: UserProfile
){
    val fullName: String
        get() = "${profile.name} ${profile.last_name}"
}

data class UserProfile(
    val id: Int,
    val name: String,
    val last_name: String,
    val type : Int,
)
