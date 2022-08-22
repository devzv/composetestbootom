package com.devzv.composetestbootom.screen.shops

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.devzv.composetestbootom.R

@Composable
fun ShopsScreen() {
    Column {
        Text(text = "Shops screen")
        val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_loading))
        LottieAnimation(composition = lottieComposition, iterations = LottieConstants.IterateForever)
    }

}