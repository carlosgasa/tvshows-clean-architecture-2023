package com.gscarlos.tvshowscarlosg.data.datasource

import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import com.gscarlos.tvshowscarlosg.data.remote.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    suspend fun loadTVShows(date: String): Flow<DataResult<List<TVShow>, DataResultError>>
    suspend fun searchTVShows(query: String): Flow<DataResult<List<TVShow>, DataResultError>>
    suspend fun detailTVShow(idShow: String): Flow<DataResult<TVShow, DataResultError>>
}