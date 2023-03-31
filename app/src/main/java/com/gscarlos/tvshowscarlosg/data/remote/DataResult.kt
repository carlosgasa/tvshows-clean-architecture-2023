package com.gscarlos.tvshowscarlosg.data.remote

import com.gscarlos.tvshowscarlosg.domain.model.TVShow

sealed class DataResult {
    class Loading(val loading: Boolean) : DataResult()
    class Error(val message: String) : DataResult()
    class Success(val tvShows: List<TVShow>) : DataResult()
}