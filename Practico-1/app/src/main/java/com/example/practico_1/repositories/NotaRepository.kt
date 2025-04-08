package com.example.practico_1.repositories

import com.example.practico_1.models.Nota

object NotaRepository {
    val notas = arrayListOf<Nota>(
        Nota(1, "Nota 1", "Contenido de la nota 1",  false),
        Nota(2, "Nota 2", "Contenido de la nota 2",  false),
        Nota(3, "Nota 3", "Contenido de la nota 3",  true),
        Nota(4, "Nota 4", "Contenido de la nota 4",  false),
        Nota(5, "Nota 5", "Contenido de la nota 5",  true)
    )

    fun getNota(): ArrayList<Nota>{
        return notas.clone() as ArrayList<Nota>
    }

    fun saveNota(nota: Nota){
        val index = notas.indexOfFirst { it.id == nota.id }
        if (index != -1) {
            notas[index] = nota
        } else {
            notas.add(1, nota)
        }
    }

    fun deleteNota(nota: Nota){
        val index = notas.indexOfFirst { it.id == nota.id }
        if (index != -1) {
            notas.removeAt(index)
        }
    }

}