package nl.birdly.graph.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import java.lang.reflect.Modifier

@Preview
@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        // TODO: Fix this compile error
//        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}