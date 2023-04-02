package com.gscarlos.tvshowscarlosg.ui.detail

import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail


sealed class DetailViewState {
    object Start : DetailViewState()
    object Loading : DetailViewState()
    class Error(val type: DataResultError) : DetailViewState()
    class SuccessData(val tvShow: TVShowDetail) : DetailViewState()
}