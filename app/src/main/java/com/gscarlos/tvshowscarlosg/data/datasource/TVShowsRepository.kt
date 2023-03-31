package com.gscarlos.tvshowscarlosg.data.datasource

interface TVShowsRepository {
    suspend fun loadTVShows()
}