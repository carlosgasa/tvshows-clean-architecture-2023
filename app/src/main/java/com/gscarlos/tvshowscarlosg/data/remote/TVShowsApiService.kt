package com.gscarlos.tvshowscarlosg.data.remote

import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsApiService {

    @GET("schedule")
    suspend fun getTvShowsToday(
        @Query("date") today: String,
        @Query("country") country: String = "US"
    ): Response<List<TVShowDto>>
}