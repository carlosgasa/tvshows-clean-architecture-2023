package com.gscarlos.tvshowscarlosg.domain.usecases

import com.gscarlos.tvshowscarlosg.domain.model.TVShow

interface UpdateFavoriteUseCase {
    fun updateFavorite(show: TVShow)
}