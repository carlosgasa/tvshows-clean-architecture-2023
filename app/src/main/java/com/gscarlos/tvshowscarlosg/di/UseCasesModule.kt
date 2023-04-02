package com.gscarlos.tvshowscarlosg.di

import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabase
import com.gscarlos.tvshowscarlosg.domain.usecases.UpdateFavoriteUseCase
import com.gscarlos.tvshowscarlosg.domain.usecases.impl.UpdateFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun providesUpdateFavoriteUseCase(db: AppDatabase): UpdateFavoriteUseCase {
        return UpdateFavoriteUseCaseImpl(db)
    }
}