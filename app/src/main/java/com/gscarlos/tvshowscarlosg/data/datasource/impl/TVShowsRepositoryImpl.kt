package com.gscarlos.tvshowscarlosg.data.datasource.impl

import android.content.Context
import com.gscarlos.tvshowscarlosg.commons.InternetUtils
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import com.gscarlos.tvshowscarlosg.data.toTvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val apiService: TVShowsApiService,
    private val context: Context
) : TVShowsRepository {
    override suspend fun loadTVShows(): Flow<DataResult> = flow {
        if (InternetUtils.isNetworkAvailable(context)) {
            emit(DataResult.Loading(true))
            apiService.getTvShowsToday("2023-03-30").apply {
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
}