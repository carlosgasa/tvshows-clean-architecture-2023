package com.gscarlos.tvshowscarlosg.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.remote.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TVShowsRepository,

    ) : ViewModel() {

    private val _tvShowsState = MutableStateFlow<HomeViewState>(HomeViewState.Start)
    val tvShowsState = _tvShowsState.asStateFlow()

    init {
        loadTVShows()
    }

    private fun loadTVShows() {
        viewModelScope.launch {
            repository.loadTVShows().collect {
                when (it) {
                    is DataResult.Error -> _tvShowsState.value = HomeViewState.Error(it.message)
                    is DataResult.Loading -> _tvShowsState.value = HomeViewState.Loading(it.loading)
                    is DataResult.Success -> _tvShowsState.value = HomeViewState.TodayTVShowsSuccess(it.tvShows)
                }
            }
        }
    }

}