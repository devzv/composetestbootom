package com.devzv.composetestbootom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.devzv.composetestbootom.ui.language.DEFAULT_LANGUAGE
import com.devzv.composetestbootom.ui.language.SupportedLanguage

class MainViewModel : ViewModel() {

    var currentLanguage by mutableStateOf(DEFAULT_LANGUAGE)

    fun onLanguageChange(newLanguage: SupportedLanguage) {
        currentLanguage = newLanguage
    }

}