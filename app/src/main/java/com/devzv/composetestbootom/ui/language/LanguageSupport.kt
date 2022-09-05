package com.devzv.composetestbootom.ui.language

import androidx.annotation.ArrayRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringArrayResource

val DEFAULT_LANGUAGE = SupportedLanguage.ENG

val LocalLanguage = compositionLocalOf { DEFAULT_LANGUAGE }

@Composable
@ReadOnlyComposable
fun getLanguageString(@ArrayRes stringName: Int): String {
    val languageIndex = LocalLanguage.current.resourceIndex
    val stringArray = stringArrayResource(stringName)
    return if (languageIndex <= stringArray.lastIndex) {
        stringArray[languageIndex]
    } else if (DEFAULT_LANGUAGE.resourceIndex <= stringArray.lastIndex) {
        stringArray[DEFAULT_LANGUAGE.resourceIndex]
    } else if (stringArray.isNotEmpty()) {
        stringArray.first()
    } else {
        ""
    }
}

enum class SupportedLanguage(val title: String, val resourceIndex: Int) {
    RU("Русский", 0),
    ENG("Английский", 1),
    LT("Латвийский", 2),
}