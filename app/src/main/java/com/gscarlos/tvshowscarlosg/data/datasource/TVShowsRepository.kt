package com.gscarlos.tvshowscarlosg.data.datasource

import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    suspend fun loadTVShows(date: String): Flow<DataResult>
    suspend fun searchTVShows(query: String): Flow<DataResult>
}