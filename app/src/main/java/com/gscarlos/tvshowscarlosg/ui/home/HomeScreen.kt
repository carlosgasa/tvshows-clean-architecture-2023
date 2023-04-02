package com.gscarlos.tvshowscarlosg.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.ui.compose.composables.OnLifecycleEvent
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowItem
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowsTopBar
import com.gscarlos.tvshowscarlosg.ui.compose.theme.BorderCardLight
import com.gscarlos.tvshowscarlosg.ui.compose.theme.GrayDark
import com.gscarlos.tvshowscarlosg.ui.compose.theme.GreenLight


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onClickItem: (String) -> Unit,
    onNavigateFavorites: () -> Unit
) {
    val showsState = viewModel.tvShowsState.collectAsState().value
    val showsSearchedState = viewModel.tvSearchedShowsState.collectAsState().value

    var todayTvShows = listOf<TVShow>()
    var searchedTvShows = listOf<TVShow>()
    var searchVisible by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    var errorType: DataResultError = DataResultError.NoError


    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.loadTVShows()
            }
            else -> {}
        }
    }

    when (showsState) {
        is HomeViewState.Error -> {
            error = true
            errorType = showsState.type
        }
        HomeViewState.Loading -> loading = true
        is HomeViewState.TVShowsSuccess -> {
            loading = false
            error = false
            todayTvShows = showsState.tvShows
        }
        HomeViewState.Start -> {}
    }
    when (showsSearchedState) {
        is HomeViewState.Error -> {
            error = true
            errorType = showsSearchedState.type
        }
        HomeViewState.Loading -> loading = true
        is HomeViewState.TVShowsSuccess -> {
            loading = false
            error = false
            searchedTvShows = showsSearchedState.tvShows
        }
        HomeViewState.Start -> {}
    }



    Scaffold(
        topBar = {
            TVShowsTopBar(
                onClose = {
                    if(searchVisible) viewModel.loadTVShows()
                    searchVisible = false
                    error = false
                },
                onSearch = {
                    searchVisible = true
                    viewModel.searchTVShows(it)
                },
                onFavorite = onNavigateFavorites
            )
        }
    ) { contentPadding ->
        when {
            loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error -> {
                ShowInfoState(errorType) {
                    viewModel.loadTVShows()
                }
            }
            searchVisible -> {
                SearchedTVShowsContent(
                    modifier = Modifier.padding(contentPadding),
                    tvShows = searchedTvShows,
                    onClickItem = onClickItem
                ) {
                    viewModel.toggleFavorite(it)
                }
            }
            else -> {
                if (todayTvShows.isEmpty()) {
                    ShowInfoState(errorType) {
                        viewModel.loadTVShows()
                    }
                } else {
                    TodayTVShowsContent(
                        modifier = Modifier.padding(contentPadding),
                        tvShows = todayTvShows,
                        onClickItem = onClickItem
                    ) {
                        viewModel.toggleFavorite(it)
                    }
                }

            }
        }
    }
}

@Composable
fun TodayTVShowsContent(
    modifier: Modifier = Modifier,
    tvShows: List<TVShow>,
    onClickItem: (String) -> Unit,
    onFavorite: (TVShow) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tvShows) {
            TVShowItem(
                show = it,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                onClickItem = onClickItem,
                onFavorite = onFavorite
            )
        }
    }
}


@Composable
fun SearchedTVShowsContent(
    modifier: Modifier = Modifier,
    tvShows: List<TVShow>,
    onClickItem: (String) -> Unit,
    onFavorite: (TVShow) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tvShows) {
            TVShowItem(
                show = it,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                onClickItem = onClickItem,
                onFavorite = onFavorite
            )
        }
    }

}

@Composable
fun ShowInfoState(
    error: DataResultError,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if(error.getImage() != 0) {
                Image(
                    modifier = Modifier
                        .padding(
                            top = 100.dp,
                            end = 100.dp,
                            start = 100.dp,
                            bottom = 20.dp
                        )
                        .size(150.dp),
                    painter = painterResource(id = error.getImage()),
                    contentDescription = "No data found"
                )
            }
            Text(
                text = error.getMessage(context),
                Modifier.padding(16.dp),
                color = MaterialTheme.colors.onBackground
            )
            error.getAction(context)?.let {
                OutlinedButton(onClick = { onClick?.invoke() }) {
                    Text(text = it, color = Color(0xFFBB86FC))
                }
            }
        }
    }
}