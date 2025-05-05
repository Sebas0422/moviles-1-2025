package com.example.practico__2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practico__2.db.dao.IngredienteDao
import com.example.practico__2.db.dao.RecetaDao
import com.example.practico__2.db.models.Ingrediente
import com.example.practico__2.db.models.Receta

@Database(
    entities = [Receta::class, Ingrediente::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun recetaDao(): RecetaDao
    abstract fun ingredienteDao(): IngredienteDao

    companion object{
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "recetario-db"
            )
                .build()
        }
    }
}