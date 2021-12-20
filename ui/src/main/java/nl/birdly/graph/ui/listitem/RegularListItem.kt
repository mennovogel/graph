package nl.birdly.graph.ui.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun RegularListItem(
    startTitle: String,
    startSubtitle: String,
    endTitle: String
    ) {
    ConstraintLayout(Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
        val (startTitleRef, startSubtitleRef, endTitleRef) = createRefs()
        Text(
            startTitle,
            Modifier.constrainAs(startTitleRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            style = MaterialTheme.typography.body1
        )
        Text(
            startSubtitle,
            Modifier.constrainAs(startSubtitleRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                top.linkTo(startTitleRef.bottom)
            },
            style = MaterialTheme.typography.body2
        )
        Text(
            endTitle,
            Modifier.constrainAs(endTitleRef) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },

            textAlign = TextAlign.End,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview(widthDp = 320, heightDp = 120)
@Composable
private fun RegularListItemPreview() {
    RegularListItem(
        startTitle = "Title",
        startSubtitle = "StartSubtitle",
        endTitle = "EndTitle"
    )
}