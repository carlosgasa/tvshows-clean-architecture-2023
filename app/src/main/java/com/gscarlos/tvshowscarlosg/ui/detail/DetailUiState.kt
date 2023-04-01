package com.gscarlos.tvshowscarlosg.ui.detail

import com.gscarlos.tvshowscarlosg.data.remote.DataResultError
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail


sealed class DetailUiState {
    object Start : DetailUiState()
    object Loading : DetailUiState()
    class Error(val type: DataResultError) : DetailUiState()
    class SuccessData(val tvShow: TVShowDetail) : DetailUiState()
}