package com.gscarlos.tvshowscarlosg.domain.usecases.impl

import com.gscarlos.tvshowscarlosg.data.local.database.AppDatabase
import com.gscarlos.tvshowscarlosg.data.toEntity
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.usecases.UpdateFavoriteUseCase
import javax.inject.Inject

class UpdateFavoriteUseCaseImpl @Inject constructor(
    private val db: AppDatabase,
) : UpdateFavoriteUseCase {

    override fun updateFavorite(show: TVShow) {
        db.tvShowDao().tvShowExistsById(show.id).let {
            if(it) {
                db.tvShowDao().deleteById(show.id)
            } else {
                db.tvShowDao().insert(show.toEntity())
            }
        }
    }

}