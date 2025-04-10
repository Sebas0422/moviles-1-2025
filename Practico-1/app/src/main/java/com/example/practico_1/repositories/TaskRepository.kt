package com.example.practico_1.repositories

import com.example.practico_1.models.Task

object TaskRepository {
    val tasks = arrayListOf<Task>(
        Task(1, "Nota 1", "Contenido de la nota 1",  false),
        Task(2, "Nota 2", "Contenido de la nota 2",  false),
        Task(3, "Nota 3", "Contenido de la nota 3",  true),
        Task(4, "Nota 4", "Contenido de la nota 4",  false),
        Task(5, "Nota 5", "Contenido de la nota 5",  true)
    )

    fun getNota(): ArrayList<Task>{
        return tasks.clone() as ArrayList<Task>
    }

    fun saveNota(task: Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        } else {
            tasks.add(1, task)
        }
    }

    fun deleteNota(task: Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks.removeAt(index)
        }
    }

    fun getNotaById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

}