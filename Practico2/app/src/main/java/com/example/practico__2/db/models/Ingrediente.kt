package com.example.practico__2.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Ingrediente (
    var nombre: String,
    var recetaId: Int
) {
    var seleccionado: Boolean = false

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}