package com.example.proyectofinal.ui.acitivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivityRegisterBinding
import com.example.proyectofinal.models.RegisterRequest
import com.example.proyectofinal.ui.viewModels.LoginViewModels

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: LoginViewModels by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnSubmitRegister.setOnClickListener {
            val name = binding.txtNameRegister.editText?.text.toString()
            val lastName = binding.txtLastNameRegister.editText?.text.toString()
            val email = binding.txtEmailRegister.editText?.text.toString()
            val password = binding.txtPasswordRegister.editText?.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && lastName.isNotEmpty()) {
                val request = RegisterRequest(name, lastName, email, password)
                registerViewModel.register(request, this)
            } else {
                binding.txtNameRegister.error = "Please fill in all fields"
            }
        }

        binding.lblLogin.setOnClickListener {
            val intent = MainActivity.createIntent(this)
            startActivity(intent)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }
}