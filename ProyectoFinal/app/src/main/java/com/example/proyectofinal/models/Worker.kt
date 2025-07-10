package com.example.proyectofinal.models

typealias WorkerList = ArrayList<Worker>

data class Worker(
    val id: Int,
    val user_id: Int,
    val picture_url: String,
    val average_rating: Int,
    val reviews_count: Int,
    val user: User,
    val categories : CategoryList,
    val reviews : ReviewList
){
    val profileScore : String
        get() = if (average_rating == 0) "Sin calificaciones" else "$average_rating% - ${reviews_count}"
}
