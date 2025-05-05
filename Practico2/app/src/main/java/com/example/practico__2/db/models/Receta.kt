    package com.example.practico__2.db.models

    import androidx.room.Entity
    import androidx.room.PrimaryKey
    @Entity
    class Receta (
        var nombre: String,
        var preparacion : String,
    ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
    }