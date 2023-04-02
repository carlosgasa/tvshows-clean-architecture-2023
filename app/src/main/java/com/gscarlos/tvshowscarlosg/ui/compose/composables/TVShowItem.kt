package com.gscarlos.tvshowscarlosg.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.ui.compose.theme.*

@Composable
fun TVShowItem(show: TVShow, modifier: Modifier, onClickItem: (String) -> Unit, onFavorite: (TVShow) -> Unit) {
    var favorite by remember { mutableStateOf(false) }
    favorite = show.favorite
    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickItem(show.id.toString()) }) {
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

            Column(modifier.weight(1f)) {
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

            IconButton(
                onClick = {
                    onFavorite(show)
                    favorite = !favorite
                }
            ) {
                Icon(
                    imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Search bar"
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
            modifier = Modifier,
            onClickItem = {}
        ) {}
    }
}