package com.example.practico__2.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico__2.R
import com.example.practico__2.db.models.Ingrediente
import com.example.practico__2.ui.adapters.IngredienteAdapter

class MainActivity : AppCompatActivity() {

    private val listaIngredientes = arrayListOf(
        Ingrediente("Tomate", 0),
        Ingrediente("Lechuga", 0),
        Ingrediente("Cebolla", 0),
        Ingrediente("Pimiento", 0),
        Ingrediente("Aguacate", 0),
        Ingrediente("Pepino", 0),
        Ingrediente("Zanahoria", 0),
        Ingrediente("Brócoli", 0),
        Ingrediente("Coliflor", 0),
        Ingrediente("Espinaca", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rvIngredientes)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = IngredienteAdapter(listaIngredientes) { ingrediente ->
            ingrediente.seleccionado = !ingrediente.seleccionado
        }

        btnBuscar.setOnClickListener {
            val seleccionados = listaIngredientes.filter { it.seleccionado }
            Toast.makeText(this, "Seleccionados: ${seleccionados.map { it.nombre }}", Toast.LENGTH_SHORT).show()
        }
    }
}