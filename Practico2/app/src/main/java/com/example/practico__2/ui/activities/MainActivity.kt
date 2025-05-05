package com.example.practico__2.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practico__2.databinding.ActivityMainBinding
import com.example.practico__2.db.models.Ingrediente
import com.example.practico__2.ui.adapters.IngredienteAdapter

class MainActivity : AppCompatActivity(), IngredienteAdapter.IngredienteClickListener {

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

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEventReciclerView()
        setupEventListener()
    }

    private fun setupEventReciclerView() {
        binding.rvIngredientes.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = IngredienteAdapter(listaIngredientes, this@MainActivity)
        }
    }

    private fun setupEventListener(){
        binding.btnBuscar.setOnClickListener {
            val seleccionados = listaIngredientes.filter { it.seleccionado }
            Toast.makeText(this, "Seleccionados: ${seleccionados.map { it.nombre }}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onIngredienteClick(position: Int, ingrediente: Ingrediente) {
        ingrediente.seleccionado = !ingrediente.seleccionado
        val adapter = binding.rvIngredientes.adapter as IngredienteAdapter
        adapter.notifyItemListChanged(position)
    }
}