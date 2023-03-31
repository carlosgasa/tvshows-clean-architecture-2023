package com.gscarlos.tvshowscarlosg.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.ui.compose.composables.BaseCard
import com.gscarlos.tvshowscarlosg.ui.compose.composables.SearchBar
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowItem
import com.gscarlos.tvshowscarlosg.ui.compose.composables.TVShowsTopBar


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val showsState = viewModel.tvShowsState.collectAsState()
    var todayTvShows = listOf<TVShow>()
    var searchVisible by remember { mutableStateOf(false) }

    when (showsState.value) {
        is HomeViewState.Error -> {}
        is HomeViewState.Loading -> {}
        HomeViewState.Start -> {}
        is HomeViewState.TodayTVShowsSuccess -> {
            todayTvShows = (showsState.value as HomeViewState.TodayTVShowsSuccess).tvShows
        }
    }

    Scaffold(
        topBar = {
            TVShowsTopBar(
                onClose = {
                    searchVisible = false
                },
                onSearch = {
                    searchVisible = true
                }
            )
        }
    ) { contentPadding ->
        if(searchVisible) {
            SearchedTVShowsContent()
        } else {
            TodayTVShowsContent(
                modifier = Modifier.padding(contentPadding),
                properties = TodayTVShowsContentProperties(todayTvShows)
            )
        }
    }
}

data class TodayTVShowsContentProperties(val tvShows: List<TVShow>)

@Composable
fun TodayTVShowsContent(modifier: Modifier = Modifier, properties: TodayTVShowsContentProperties) {
    LazyColumn(
        modifier = modifier
    ) {
        items(properties.tvShows) {
            TVShowItem(it, modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
        }
    }
}


@Composable
fun SearchedTVShowsContent() {

}