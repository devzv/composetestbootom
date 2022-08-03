package com.devzv.composetestbootom.screen.registration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.math.log

class PasswordViewModel(
    private val registrationUseCase: RegistrationUseCase,
) : ViewModel() {

    class Factory(private val registrationUseCase: RegistrationUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PasswordViewModel(registrationUseCase) as T
        }
    }

    var registerSuccess by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set

    init {
        Log.d("PasswordViewModel", "init: ${hashCode()}")
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onApplyClick(login: String) {
        if (login.isNotEmpty()) {
            registerSuccess = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("PasswordViewModel", "onCleared: ${hashCode()}")
    }

}