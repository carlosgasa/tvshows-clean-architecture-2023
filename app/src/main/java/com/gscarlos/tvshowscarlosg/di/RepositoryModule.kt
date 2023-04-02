package com.gscarlos.tvshowscarlosg.di

import android.content.Context
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.datasource.impl.TVShowsRepositoryImpl
import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabase
import com.gscarlos.tvshowscarlosg.data.remote.TVShowsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTVShowsRepository(apiService: TVShowsApiService, db: AppDatabase, @ApplicationContext context: Context): TVShowsRepository {
        return TVShowsRepositoryImpl(apiService, db, context)
    }
}