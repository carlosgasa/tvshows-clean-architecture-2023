package com.gscarlos.tvshowscarlosg.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.ui.compose.theme.*

@Composable
fun TVShowItem(show: TVShow, modifier: Modifier) {
    BaseCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Image(
                modifier = Modifier
                    .size(75.dp, 100.dp)
                    .padding(8.dp),
                painter = rememberImagePainter(
                    data = show.imageMedium,
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
                contentDescription = "ShowTv item showed"
            )

            Column() {
                Text(
                    text = show.name,
                    color = MaterialTheme.colors.onBackground,
                    style = Typography.subtitle1
                )
                Text(
                    text = show.network,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = show.dates,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

@Preview
@Composable
fun TVShowItemPreview() {
    TvShowsTheme() {
        TVShowItem(
            show = TVShow(
                id = 1,
                name = "Show test",
                network = "NBC",
                dates = "yyyy- mm-dd | 09:76",
                imageMedium = ""
            ),
            modifier = Modifier
        )
    }
}