package com.example.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tdLib.TelegramRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repository : TelegramRepository

    val authState = repository.getAuthStateFlow()

    fun performAuthResult() {
        repository.checkAuthState()
    }

    fun sendPhone(phone: String) {
        viewModelScope.launch {
            repository.sendPhone(phone)
        }
    }

    fun sendCode(code: String) {
        viewModelScope.launch {
            repository.sendCode(code)
        }
    }

    fun sendPassword(password: String) {
        viewModelScope.launch {
            repository.sendPassword(password)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.exit()
        }
    }

}

