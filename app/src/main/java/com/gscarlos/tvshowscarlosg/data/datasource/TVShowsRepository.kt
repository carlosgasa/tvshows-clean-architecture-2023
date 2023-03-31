package com.gscarlos.tvshowscarlosg.data.datasource

import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    suspend fun loadTVShows(): Flow<DataResult>
}