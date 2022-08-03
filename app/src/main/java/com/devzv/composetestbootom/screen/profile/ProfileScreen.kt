package com.devzv.composetestbootom.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    onRegClick: () -> Unit
) {
    Column {
        Text(text = "Profile screen")
        Button(onClick = onRegClick) {
            Text(text = "Registration")
        }
    }
}