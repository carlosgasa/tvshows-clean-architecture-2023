package com.gscarlos.tvshowscarlosg.di

import com.gscarlos.tvshowscarlosg.commons.Constants.TV_SHOWS_BASE_URL
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(): TVShowsApiService {
        return Retrofit
            .Builder()
            .baseUrl(TV_SHOWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TVShowsApiService::class.java)
    }
}