package com.gscarlos.tvshowscarlosg.data.datasource

import com.gscarlos.tvshowscarlosg.data.DataResult
import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
    suspend fun loadTVShows(date: String): Flow<DataResult<List<TVShow>, DataResultError>>
    suspend fun searchTVShows(query: String): Flow<DataResult<List<TVShow>, DataResultError>>
    suspend fun detailTVShow(idShow: String): Flow<DataResult<TVShowDetail, DataResultError>>
    suspend fun getFavorites(): Flow<List<TVShow>>
}