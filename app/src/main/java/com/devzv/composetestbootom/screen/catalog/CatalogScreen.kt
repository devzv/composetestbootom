package com.devzv.composetestbootom.screen.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.devzv.composetestbootom.Database
import com.devzv.composetestbootom.data.getImages
import com.devzv.composetestbootom.ui.theme.ComposeTestBootomTheme
import org.kodein.di.compose.androidContextDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "First level screen")
        Button(onClick = onNext) {
            Text(text = "Next")
        }
        withDI(di = androidContextDI()) {
            val db: Database by rememberInstance()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                getImages(db).forEach {
                    item {
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.Gray),
                            model = it,
                            contentDescription = null
                        )
                    }
                }
            }
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

@Preview(showSystemUi = true)
@Composable
fun CatalogFirstLevelScreenPreview() {
    ComposeTestBootomTheme {
        CatalogFirstLevelScreen {

        }
    }
}
