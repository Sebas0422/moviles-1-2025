package com.example.practico_1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico_1.R
import com.example.practico_1.databinding.ActivityTaskFormBinding
import com.example.practico_1.models.Task
import com.example.practico_1.repositories.TaskRepository

class TaskFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var currentTask: Task
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        currentTask = intent.getSerializableExtra(PARAM_NOTA, Task::class.java) as Task
        binding.txtTitleFrom.editText?.setText(currentTask.title)
        binding.txtContentFrom.editText?.setText(currentTask.content)
        binding.cbIsChecked.isChecked = currentTask.isChecked
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnSaveNota.setOnClickListener {
            currentTask.title = binding.txtTitleFrom.editText?.text.toString()
            currentTask.content = binding.txtContentFrom.editText?.text.toString()
            currentTask.isChecked = binding.cbIsChecked.isChecked

            TaskRepository.saveNota(currentTask)
            finish()
        }

        binding.btnCancelNota.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val PARAM_NOTA = "NOTA"
        fun detailIntent(context: Context, task: Task): Intent {
            val intent = Intent(context, TaskFormActivity::class.java)
            intent.putExtra(PARAM_NOTA, task)
            return intent
        }
    }
}