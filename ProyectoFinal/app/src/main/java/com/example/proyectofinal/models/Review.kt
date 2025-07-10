package com.example.proyectofinal.models

typealias ReviewList = ArrayList<Review>

data class Review (
    val id: Int,
    val worker_id: Int,
    val user_id: Int,
    val appointment_id: Int,
    val rating: Int,
    val comment : String ? = null,
    val is_done: Int,
    val user: User
)