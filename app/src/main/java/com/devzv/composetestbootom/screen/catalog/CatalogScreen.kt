package com.devzv.composetestbootom.screen.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CatalogTopLevelScreen(onNext: () -> Unit) {
    Column {
        Text(text = "Top level screen")
        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }

}

@Composable
fun CatalogFirstLevelScreen(onNext: () -> Unit) {
    Column {
        Text(text = "First level screen")
        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }
}

@Composable
fun CatalogSecondLevelScreen(onBack: () -> Unit) {
    Column {
        Text(text = "Second level screen")
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}
