package com.example.practico__2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practico__2.db.models.Receta
@Dao
interface RecetaDao {
    @Query("SELECT * FROM Receta")
    suspend fun getAllRecetas(): List<Receta>

    @Insert
    suspend fun insertReceta(receta: Receta): Long

    @Query("SELECT * FROM Receta WHERE id = :id")
    suspend fun getRecetaById(id: Int): Receta

    @Query("DELETE FROM Receta WHERE id = :id")
    suspend fun deleteRecetaById(id: Int): Int

    @Update
    suspend fun updateReceta(receta: Receta): Int
}