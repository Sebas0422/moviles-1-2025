package com.example.practico__2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practico__2.db.models.Ingrediente
@Dao
interface IngredienteDao {
    @Query("SELECT * FROM Ingrediente")
    suspend fun getAllIngredientes(): List<Ingrediente>

    @Insert
    suspend fun insertIngrediente(ingrediente: Ingrediente): Long

    @Query("SELECT * FROM Ingrediente WHERE id = :id")
    suspend fun getIngredienteById(id: Int): Ingrediente

    @Query("DELETE FROM Ingrediente WHERE id = :id")
    suspend fun deleteIngredienteById(id: Int): Int

    @Update
    suspend fun updateIngrediente(ingrediente: Ingrediente): Int

}