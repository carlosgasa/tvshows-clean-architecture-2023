package com.gscarlos.tvshowscarlosg.ui.home

import com.gscarlos.tvshowscarlosg.domain.model.TVShow

sealed class HomeViewState {
    object Start : HomeViewState()
    object Loading : HomeViewState()
    class Error(val message: String) : HomeViewState()
    class TVShowsSuccess(val tvShows: List<TVShow>) : HomeViewState()
}
