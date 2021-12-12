package nl.birdly.graph.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import nl.birdly.graph.ui.R

@Preview
@Composable
fun ErrorScreen(
    errorTitle: String = LocalContext.current.getString(R.string.error_title_default),
    errorText: String = LocalContext.current.getString(R.string.error_text_default)) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        // TODO: Fix this compile error
//        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            errorTitle,
            style = MaterialTheme.typography.h6
        )
        Text(
            errorText,
            style = MaterialTheme.typography.body1
        )
    }
}