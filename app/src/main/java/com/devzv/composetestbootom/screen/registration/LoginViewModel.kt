package com.devzv.composetestbootom.screen.registration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var login by mutableStateOf("")
        private set

    init {
        Log.d("LoginViewModel", "init: ${hashCode()}")
    }

    fun onLoginChanged(newLogin: String) {
        login = newLogin
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("LoginViewModel", "onCleared: ${hashCode()}")
    }

}