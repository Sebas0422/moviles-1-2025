package com.example.practico_1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_1.R
import com.example.practico_1.databinding.ActivityMainBinding
import com.example.practico_1.models.Nota
import com.example.practico_1.repositories.NotaRepository
import com.example.practico_1.ui.adapters.NotaAdapter

class MainActivity : AppCompatActivity(), NotaAdapter.OnNotaClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvNotas: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvNotas = binding.rvNotas
        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCreateNota.setOnClickListener {
            val notas = NotaRepository.getNota()
            val nota = Nota(notas.size+1, "","",false)
            intentActivity(nota)
        }
    }

    private fun intentActivity(nota: Nota) {
        NotaFormActivity.detailIntent(this, nota).also {
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }

    private fun reloadData() {
        val notas = NotaRepository.getNota()
        val adapter = rvNotas.adapter as NotaAdapter
        adapter.setData(notas)
    }

    private fun setupRecyclerView() {
        rvNotas.adapter = NotaAdapter(NotaRepository.notas, this)
        rvNotas.layoutManager = LinearLayoutManager(this)
    }

    override fun onNotaEditClickListener(nota: Nota) {
        intentActivity(nota)
    }

    override fun onNotaDeleteClickListener(nota: Nota) {
        NotaRepository.deleteNota(nota)
        reloadData()
    }
}