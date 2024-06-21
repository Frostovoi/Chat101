package com.example.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
inline fun <reified T : ViewModel> loginDaggerViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T
) : T = viewModel(
    modelClass = T::class.java,
    key = key,
    factory = object : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInstanceCreator() as T
        }
    }


    )

