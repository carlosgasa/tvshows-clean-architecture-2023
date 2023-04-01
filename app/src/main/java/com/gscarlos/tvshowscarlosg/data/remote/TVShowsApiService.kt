package com.gscarlos.tvshowscarlosg.data.remote

import com.gscarlos.tvshowscarlosg.data.remote.responses.Show
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowSearchedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowsApiService {

    @GET("schedule")
    suspend fun getTvShowsToday(
        @Query("date") today: String,
        @Query("country") country: String = "US"
    ): Response<List<TVShowDto>>

    @GET("search/shows")
    suspend fun searchTVShows(
        @Query("q") query: String
    ): Response<List<TVShowSearchedDto>>

    @GET("/shows/{id}")
    suspend fun getShowDetail(
        @Path("id") idShow: String
    ): Response<Show>
}