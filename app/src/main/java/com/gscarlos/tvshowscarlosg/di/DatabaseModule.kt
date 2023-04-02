package com.gscarlos.tvshowscarlosg.di

import android.content.Context
import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabase
import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabaseBuilder.getInstance(applicationContext)
    }
}