package com.gscarlos.tvshowscarlosg.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: TVShowsRepository,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
): ViewModel() {

    private val _favoriteState = MutableStateFlow<List<TVShow>>(emptyList())
    val favoriteState = _favoriteState

    init {
        viewModelScope.launch {
            repository.getFavorites().collectLatest {
                _favoriteState.value = it
            }
        }
    }

    fun updateFavorite(tvShow: TVShow) {
        updateFavoriteUseCase.updateFavorite(tvShow)
    }
}