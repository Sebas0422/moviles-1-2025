package com.example.proyectofinal.ui.acitivities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.example.proyectofinal.models.LoginRequest
import com.example.proyectofinal.token.TokenManager
import com.example.proyectofinal.ui.viewModels.LoginViewModels
import java.util.logging.Logger
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewmodels : LoginViewModels by viewModels()
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
        setupEventListeners()
        setupEventViewModelObservers()
    }

    private fun setupEventViewModelObservers() {
        loginViewmodels.loginState.observe(this) { loginState ->
            if(loginState == null) {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                return@observe
            }
            TokenManager.token = loginState.access_token
            val intent = ViewUserAcitivity.createIntent(this)
            startActivity(intent)
        }
    }

    private fun setupEventListeners() {
        binding.btnSubmitLogin.setOnClickListener {
            val email = binding.txtEmailLogin.editText?.text.toString()
            val password = binding.txtPasswordLogin.editText?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val user = LoginRequest(email, password)
                loginViewmodels.login(user)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}