package com.example.practico_3_4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practico_3_4.db.dao.ScoreDao
import com.example.practico_3_4.db.models.Score

@Database(
    entities = [Score::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "score-db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}