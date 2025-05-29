package com.example.practico_3_4.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    var score: Int = 0,
    var name: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}