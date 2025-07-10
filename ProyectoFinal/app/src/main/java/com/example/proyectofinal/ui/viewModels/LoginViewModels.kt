package com.example.proyectofinal.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.models.LoginRequest
import com.example.proyectofinal.models.LoginResponse
import com.example.proyectofinal.models.RegisterRequest
import com.example.proyectofinal.repositories.AuthRepository
import com.example.proyectofinal.ui.acitivities.MainActivity
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

    fun register(request: RegisterRequest, context: Context) {
        viewModelScope.launch {
            try {
                AuthRepository.register(request)
                val intent = MainActivity.createIntent(context)
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("Register error", "Error during registration", e)
            }
        }
    }
}