package com.gscarlos.tvshowscarlosg.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscarlos.tvshowscarlosg.commons.toShortFormat
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.DataResult
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TVShowsRepository,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel() {

    private val _tvShowsState = MutableStateFlow<HomeViewState>(HomeViewState.Start)
    val tvShowsState = _tvShowsState.asStateFlow()

    private val _tvSearchedShowsState = MutableStateFlow<HomeViewState>(HomeViewState.Start)
    val tvSearchedShowsState = _tvSearchedShowsState.asStateFlow()

    init {
        loadTVShows()
    }

    fun loadTVShows() {
        viewModelScope.launch {
            repository.loadTVShows(Date().toShortFormat()).collect {
                when (it) {
                    is DataResult.Error -> _tvShowsState.value = HomeViewState.Error(it.errorType)
                    DataResult.Loading -> _tvShowsState.value = HomeViewState.Loading
                    is DataResult.Success -> _tvShowsState.value = HomeViewState.TVShowsSuccess(it.data)
                }
            }
        }
    }

    fun searchTVShows(query: String) {
        viewModelScope.launch {
            repository.searchTVShows(query).collect {
                when (it) {
                    is DataResult.Error -> _tvSearchedShowsState.value = HomeViewState.Error(it.errorType)
                    DataResult.Loading -> _tvSearchedShowsState.value = HomeViewState.Loading
                    is DataResult.Success -> _tvSearchedShowsState.value = HomeViewState.TVShowsSuccess(it.data)
                }
            }
        }
    }

    fun toggleFavorite(tvShow: TVShow) {
        updateFavoriteUseCase.updateFavorite(tvShow)
    }

}