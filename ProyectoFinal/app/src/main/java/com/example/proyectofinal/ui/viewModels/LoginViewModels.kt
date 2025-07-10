package com.example.proyectofinal.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.models.LoginRequest
import com.example.proyectofinal.models.LoginResponse
import com.example.proyectofinal.models.RegisterRequest
import com.example.proyectofinal.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModels: ViewModel() {

    private val _loginState = MutableLiveData<LoginResponse>()
    val loginState: LiveData<LoginResponse> get() = _loginState

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = AuthRepository.login(request)
                _loginState.value = response
            } catch (e: Exception) {
                Log.e("Login error", "Error during login", e)
            }
        }
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = AuthRepository.register(request)
                Log.i("Register", "Registration successful: $response")
            } catch (e: Exception) {
                Log.e("Register error", "Error during registration", e)
            }
        }
    }
}