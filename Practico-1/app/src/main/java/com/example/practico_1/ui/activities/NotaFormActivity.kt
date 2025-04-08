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
import com.example.practico_1.databinding.ActivityNotaFormBinding
import com.example.practico_1.models.Nota
import com.example.practico_1.repositories.NotaRepository

class NotaFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotaFormBinding
    private lateinit var currentNota: Nota
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        currentNota = intent.getSerializableExtra(PARAM_NOTA, Nota::class.java) as Nota
        binding.txtTitleFrom.editText?.setText(currentNota.title)
        binding.txtContentFrom.editText?.setText(currentNota.content)
        binding.cbIsChecked.isChecked = currentNota.isChecked
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnSaveNota.setOnClickListener {
            currentNota.title = binding.txtTitleFrom.editText?.text.toString()
            currentNota.content = binding.txtContentFrom.editText?.text.toString()
            currentNota.isChecked = binding.cbIsChecked.isChecked

            NotaRepository.saveNota(currentNota)
            finish()
        }

        binding.btnCancelNota.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val PARAM_NOTA = "NOTA"
        fun detailIntent(context: Context, nota: Nota): Intent {
            val intent = Intent(context, NotaFormActivity::class.java)
            intent.putExtra(PARAM_NOTA, nota)
            return intent
        }
    }
}