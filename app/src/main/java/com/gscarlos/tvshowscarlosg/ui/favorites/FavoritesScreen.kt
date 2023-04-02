package com.gscarlos.tvshowscarlosg.ui.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gscarlos.tvshowscarlosg.R
import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowItem
import com.gscarlos.tvshowscarlosg.ui.compose.theme.GrayDark
import com.gscarlos.tvshowscarlosg.ui.compose.theme.Typography
import com.gscarlos.tvshowscarlosg.ui.home.ShowInfoState

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = viewModel(),
    onClickItem: (String) -> Unit,
    onBack: () -> Unit,
) {
    val favoritesState = viewModel.favoriteState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = GrayDark,
            elevation = 0.dp,
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 2.dp),
            contentColor = Color.Black
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        onBack()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = Color.White,
                        contentDescription = "Back button"
                    )
                }
                Text(text = stringResource(id = R.string.txt_favs), style = Typography.subtitle2, color = Color.White)

            }
        }
    }) { contentPadding ->
        if(favoritesState.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(14.dp),
            ) {
                items(favoritesState.value) { recipe ->
                    TVShowItem(
                        show = recipe,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        onClickItem = onClickItem,
                        onFavorite = {
                            viewModel.updateFavorite(it)
                        }
                    )

                }
            }
        } else {
            ShowInfoState(DataResultError.NoFavorites) {
            }
        }

    }
}