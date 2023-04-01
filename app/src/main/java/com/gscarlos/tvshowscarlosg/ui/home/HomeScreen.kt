package com.gscarlos.tvshowscarlosg.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowItem
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowsTopBar


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val showsState = viewModel.tvShowsState.collectAsState().value
    val showsSearchedState = viewModel.tvSearchedShowsState.collectAsState().value
    var todayTvShows = listOf<TVShow>()
    var searchedTvShows = listOf<TVShow>()
    var searchVisible by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    when (showsState) {
        is HomeViewState.Error -> {
            error = true
            errorMessage = showsState.message
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
            errorMessage = showsSearchedState.message
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
                    searchVisible = false
                },
                onSearch = {
                    searchVisible = true
                    viewModel.searchTVShows(it)
                }
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
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage)
                }
            }
            searchVisible -> {
                SearchedTVShowsContent(
                    modifier = Modifier.padding(contentPadding),
                    properties = SearchedTVShowsContentProperties(searchedTvShows)
                )
            }
            else -> {
                TodayTVShowsContent(
                    modifier = Modifier.padding(contentPadding),
                    properties = TodayTVShowsContentProperties(todayTvShows)
                )
            }
        }
    }
}

data class TodayTVShowsContentProperties(val tvShows: List<TVShow>)
data class SearchedTVShowsContentProperties(val tvShows: List<TVShow>)

@Composable
fun TodayTVShowsContent(
    modifier: Modifier = Modifier,
    properties: TodayTVShowsContentProperties
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(properties.tvShows) {
            TVShowItem(it, modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
        }
    }
}


@Composable
fun SearchedTVShowsContent(
    modifier: Modifier = Modifier,
    properties: SearchedTVShowsContentProperties
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(properties.tvShows) {
            TVShowItem(it, modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
        }
    }

}