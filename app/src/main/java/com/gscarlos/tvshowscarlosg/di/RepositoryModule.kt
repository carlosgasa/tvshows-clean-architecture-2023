package com.gscarlos.tvshowscarlosg.di

import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.datasource.impl.TVShowsRepositoryImpl
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTVShowsRepository(apiService: TVShowsApiService): TVShowsRepository {
        return TVShowsRepositoryImpl(apiService)
    }
}