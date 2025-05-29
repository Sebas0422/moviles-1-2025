package com.example.practico_3_4.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practico_3_4.db.models.Score

@Dao
interface ScoreDao {
    @Query("SELECT * FROM Score")
    suspend fun getAllScores(): List<Score>

    @Insert
    suspend fun insertScore(score: Score): Long
}