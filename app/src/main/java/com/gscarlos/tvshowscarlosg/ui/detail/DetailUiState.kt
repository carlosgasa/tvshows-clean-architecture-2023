package com.gscarlos.tvshowscarlosg.ui.detail

import com.gscarlos.tvshowscarlosg.domain.model.TVShow


sealed class DetailUiState {
    object Start : DetailUiState()
    class SuccessData(val tvShow: TVShow) : DetailUiState()
}