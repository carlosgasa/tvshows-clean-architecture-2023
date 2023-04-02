package com.gscarlos.tvshowscarlosg.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.gscarlos.tvshowscarlosg.domain.model.Person
import com.gscarlos.tvshowscarlosg.ui.compose.theme.TvShowsTheme

@Composable
fun PersonItem(person: Person) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .size(75.dp, 100.dp)
                .padding(8.dp),
            painter = rememberImagePainter(
                data = person.image,
                builder = {
                    transformations(
                        RoundedCornersTransformation(
                            8f,
                            8f,
                            8f,
                            8f
                        )
                    )
                }
            ),
            contentScale = ContentScale.FillHeight,
            contentDescription = "Person item showed"
        )
        Text(
            text = person.name,
            Modifier.size(100.dp, 20.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PersonItemPreview() {
    TvShowsTheme() {
        PersonItem(
            person = Person(
                id = 1,
                name = "Carlos Galindo",
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/49/124282.jpg"
            )
        )
    }
}