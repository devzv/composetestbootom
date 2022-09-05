package com.devzv.composetestbootom.screen.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    login: String,
    onValueChanged: (String) -> Unit,
    onNextClick: () -> Unit,
) {
    Column {
        Text(text = "Login")
        TextField(
            value = login,
            onValueChange = onValueChanged,
        )
        Button(onClick = onNextClick) {
            Text(text = "Next")
        }
    }

}

@Composable
fun PasswordScreen(
    password: String,
    onValueChanged: (String) -> Unit,
    onApplyClick: () -> Unit,
) {
    Column {
        Text(text = "Password")
        TextField(
            value = password,
            onValueChange = onValueChanged,
        )
        Button(onClick = onApplyClick) {
            Text(text = "Apply")
        }
    }
}