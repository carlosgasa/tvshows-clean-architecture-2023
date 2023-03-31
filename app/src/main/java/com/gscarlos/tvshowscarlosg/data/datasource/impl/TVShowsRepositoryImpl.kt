package com.gscarlos.tvshowscarlosg.data.datasource.impl

import android.util.Log
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val apiService: TVShowsApiService
) : TVShowsRepository {
    override suspend fun loadTVShows() {
        apiService.getTvShowsToday("2023-03-30").apply {
            if(isSuccessful) {
                Log.i("TVShowsRepositoryImpl", "loadTVShows: ${body()}")
            } else {
                Log.wtf("TVShowsRepositoryImpl", "loadTVShows: ", )
            }
        }

    }
}