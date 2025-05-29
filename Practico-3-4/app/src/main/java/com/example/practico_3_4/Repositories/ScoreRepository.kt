package com.example.practico_3_4.Repositories

import android.content.Context
import com.example.practico_3_4.db.AppDatabase
import com.example.practico_3_4.db.models.Score

object ScoreRepository {
    suspend fun insertScore(context: Context, score: Score) {
        val db = AppDatabase.getInstance(context)
        db.scoreDao().insertScore(score)
    }

    suspend fun getAllScores(context: Context): List<Score> {
        val db = AppDatabase.getInstance(context)
        return db.scoreDao().getAllScores()
    }
}