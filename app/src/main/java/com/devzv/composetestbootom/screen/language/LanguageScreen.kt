package com.devzv.composetestbootom.screen.language

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devzv.composetestbootom.R
import com.devzv.composetestbootom.ui.language.LocalLanguage
import com.devzv.composetestbootom.ui.language.SupportedLanguage
import com.devzv.composetestbootom.ui.language.getLanguageString
import com.devzv.composetestbootom.ui.theme.ComposeTestBootomTheme

@Composable
fun LanguageScreen(onLanguageChange: (SupportedLanguage) -> Unit) {

    Column {
        SupportedLanguage.values().forEach { lang ->
            Row {
                RadioButton(
                    selected = (lang == LocalLanguage.current),
                    onClick = {
                        onLanguageChange(lang)
                    })
                Text(
                    text = lang.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = getLanguageString(stringName = R.array.test_text))
    }

}

@Preview
@Composable
fun LanguageScreenPreview() {
    ComposeTestBootomTheme {
        LanguageScreen(onLanguageChange = {})
    }
}
