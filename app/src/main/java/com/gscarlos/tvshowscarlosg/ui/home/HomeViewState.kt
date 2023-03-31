package com.gscarlos.tvshowscarlosg.ui.home

import com.gscarlos.tvshowscarlosg.domain.model.TVShow

sealed class HomeViewState {
    object Start : HomeViewState()
    class Loading(val loading: Boolean) : HomeViewState()
    class Error(val message: String) : HomeViewState()
    class TodayTVShowsSuccess(val tvShows: List<TVShow>) : HomeViewState()
}
