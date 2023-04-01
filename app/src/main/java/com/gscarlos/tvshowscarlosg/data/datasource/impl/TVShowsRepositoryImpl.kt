package com.gscarlos.tvshowscarlosg.data.datasource.impl

import android.content.Context
import com.gscarlos.tvshowscarlosg.commons.InternetUtils
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import com.gscarlos.tvshowscarlosg.data.toTvShow
import com.gscarlos.tvshowscarlosg.data.toTvShowSearched
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val apiService: TVShowsApiService,
    private val context: Context
) : TVShowsRepository {
    override suspend fun loadTVShows(date: String): Flow<DataResult> = flow {
        if (InternetUtils.isNetworkAvailable(context)) {
            emit(DataResult.Loading(true))
            apiService.getTvShowsToday(date).apply {
                emit(DataResult.Loading(false))
                if (isSuccessful) {
                    body()?.let { listResult ->
                        listResult.map { dto ->
                            dto.toTvShow()
                        }.let {
                            emit(DataResult.Success(it))
                        }
                    }
                } else {
                    emit(DataResult.Error("Error, intente mas tarde"))
                }
            }
        } else {
            emit(DataResult.Error("Parece que no tienes Internet"))
        }
    }

    override suspend fun searchTVShows(query: String): Flow<DataResult> = flow {
        if (InternetUtils.isNetworkAvailable(context)) {
            emit(DataResult.Loading(true))
            apiService.searchTVShows(query).apply {
                emit(DataResult.Loading(false))
                if (isSuccessful) {
                    body()?.let { listResult ->
                        if(listResult.isEmpty()) {
                            emit(DataResult.Error("No se encontraron datos"))
                        } else {
                            listResult.map { dto ->
                                dto.toTvShowSearched()
                            }.let {
                                emit(DataResult.Success(it))
                            }
                        }

                    }
                } else {
                    emit(DataResult.Error("Error, intente mas tarde"))
                }
            }
        } else {
            emit(DataResult.Error("Parece que no tienes Internet"))
        }
    }
}