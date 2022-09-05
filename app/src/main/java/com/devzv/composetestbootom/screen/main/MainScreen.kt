package com.devzv.composetestbootom.screen.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devzv.composetestbootom.R
import com.devzv.composetestbootom.ui.language.getLanguageString
import kotlin.math.min

@Composable
fun MainScreen() {
    Box {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(state = scrollState)) {
            val text = getLanguageString(stringName = R.array.main_screen_text)
            (1..100).forEach {
                Text(text = "$text: $it")
            }
        }

        if (scrollState.value > 0) {
            var cardHeight by remember { mutableStateOf(0) }
            var cardOffset by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current

            var modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

            if (cardHeight == 0) {
                modifier = modifier
                    .alpha(0F)
                    .onGloballyPositioned { layoutCoordinates ->
                        cardHeight = layoutCoordinates.size.height
                    }
            }
            with(density) {
                cardOffset = (cardHeight - min(cardHeight, scrollState.value)).toDp()
            }

            Log.d("MainScreen", "cardHeight: $cardHeight / cardOffset: $cardOffset")

            Card(
                backgroundColor = Color.Green,
                modifier = modifier.offset(y = -cardOffset)
            ) {
                Column {
                    Text(text = "Card title")
                    Text(text = "Card text")
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}