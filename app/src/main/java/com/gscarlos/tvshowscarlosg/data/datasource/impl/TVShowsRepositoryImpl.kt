package com.gscarlos.tvshowscarlosg.data.datasource.impl

import android.content.Context
import android.util.Log
import com.gscarlos.tvshowscarlosg.commons.InternetUtils
import com.gscarlos.tvshowscarlosg.data.*
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabase
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val apiService: TVShowsApiService,
    private val db: AppDatabase,
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
                                dto.toTvShow(db.tvShowDao().tvShowExistsById(dto.id))
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
                                    dto.toTvShowSearched(
                                        db.tvShowDao().tvShowExistsById(dto.show.id)
                                    )
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

    override suspend fun detailTVShow(idShow: String): Flow<DataResult<TVShowDetail, DataResultError>> =
        flow {
            if (InternetUtils.isNetworkAvailable(context)) {
                emit(DataResult.Loading)
                val detailResponse = apiService.getTvShowDetail(idShow)
                val castResponse = apiService.getCastFromTvShow(idShow)

                if (detailResponse.isSuccessful && detailResponse.body() != null) {
                    if (castResponse.isSuccessful && castResponse.body() != null) {
                        emit(
                            DataResult.Success(
                                data = detailResponse.body()!!.toTvShowDetail(
                                    db.tvShowDao().tvShowExistsById(detailResponse.body()!!.id),
                                    castResponse.body()!!.map { it.person.toPerson() }
                                )
                            )
                        )
                    } else {
                        emit(
                            DataResult.Success(
                                data = detailResponse.body()!!.toTvShowDetail(
                                    db.tvShowDao().tvShowExistsById(detailResponse.body()!!.id)
                                )
                            )
                        )
                    }
                } else {
                    emit(DataResult.Error(DataResultError.ResultEmpty))
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternetError))
            }
        }
}