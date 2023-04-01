package com.gscarlos.tvshowscarlosg.data.datasource.impl

import android.content.Context
import com.gscarlos.tvshowscarlosg.commons.InternetUtils
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import com.gscarlos.tvshowscarlosg.data.remote.DataResultError
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import com.gscarlos.tvshowscarlosg.data.toTvShow
import com.gscarlos.tvshowscarlosg.data.toTvShowSearched
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val apiService: TVShowsApiService,
    private val context: Context
) : TVShowsRepository {
    override suspend fun loadTVShows(date: String): Flow<DataResult<List<TVShow>, DataResultError>> =
        flow {
            if (InternetUtils.isNetworkAvailable(context)) {
                emit(DataResult.Loading)
                apiService.getTvShowsToday(date).apply {
                    if (isSuccessful && body() != null) {
                        body()?.let { listResult ->
                            listResult.map { dto ->
                                dto.toTvShow()
                            }.let {
                                emit(DataResult.Success(it))
                            }
                        }
                    } else {
                        emit(DataResult.Error(DataResultError.ServiceError))
                    }
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternetError))
            }
        }

    override suspend fun searchTVShows(query: String): Flow<DataResult<List<TVShow>, DataResultError>> =
        flow {
            if (InternetUtils.isNetworkAvailable(context)) {
                emit(DataResult.Loading)
                apiService.searchTVShows(query).apply {
                    if (isSuccessful && body() != null) {
                        body()?.let { listResult ->
                            if (listResult.isEmpty()) {
                                emit(DataResult.Error(DataResultError.ResultEmpty))
                            } else {
                                listResult.map { dto ->
                                    dto.toTvShowSearched()
                                }.let {
                                    emit(DataResult.Success(it))
                                }
                            }

                        }
                    } else {
                        emit(DataResult.Error(DataResultError.ServiceError))
                    }
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternetErrorSearch))
            }
        }

    override suspend fun detailTVShow(idShow: String): Flow<DataResult<TVShow, DataResultError>> =
        flow {
            if (InternetUtils.isNetworkAvailable(context)) {
                emit(DataResult.Loading)
                apiService.getShowDetail(idShow).apply {
                    if (isSuccessful && body() != null) {
                        emit(DataResult.Success(data = body()!!.toTvShow()))
                    } else {
                        emit(DataResult.Error(DataResultError.ServiceError))
                    }
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternetError))
            }
        }
}