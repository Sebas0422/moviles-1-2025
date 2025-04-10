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
import com.example.practico_1.models.Task
import com.example.practico_1.repositories.TaskRepository
import com.example.practico_1.ui.adapters.TaskAdapter

class MainActivity : AppCompatActivity(), TaskAdapter.OnNotaClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvTask: RecyclerView
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
        rvTask = binding.rvNotas
        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCreateNota.setOnClickListener {
            val notas = TaskRepository.getNota()
            val task = Task(notas.size+1, "","",false)
            intentActivity(task)
        }
    }

    private fun intentActivity(task: Task) {
        TaskFormActivity.detailIntent(this, task).also {
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }

    private fun reloadData() {
        val tasks = TaskRepository.getNota()
        val adapter = rvTask.adapter as TaskAdapter
        adapter.setData(tasks)
    }

    private fun setupRecyclerView() {
        rvTask.adapter = TaskAdapter(TaskRepository.tasks, this)
        rvTask.layoutManager = LinearLayoutManager(this)
    }

    override fun onNotaEditClickListener(task: Task) {
        intentActivity(task)
    }

    override fun onNotaDeleteClickListener(task: Task) {
        TaskRepository.deleteNota(task)
        reloadData()
    }

    override fun onNotaColorClickListener(item: Task, parseColor: Int) {
        TaskRepository.getNotaById(item.id)?.let {
            it.color = parseColor
            TaskRepository.saveNota(it)
            reloadData()
        }
    }
}