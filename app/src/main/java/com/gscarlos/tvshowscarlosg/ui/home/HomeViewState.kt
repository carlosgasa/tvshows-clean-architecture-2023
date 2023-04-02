package com.gscarlos.tvshowscarlosg.ui.home

import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShow

sealed class HomeViewState {
    object Start : HomeViewState()
    object Loading : HomeViewState()
    class Error(val type: DataResultError) : HomeViewState()
    class TVShowsSuccess(val tvShows: List<TVShow>) : HomeViewState()
}
