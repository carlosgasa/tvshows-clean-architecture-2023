package com.gscarlos.tvshowscarlosg.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TVShowsRepository,

    ) : ViewModel() {

    fun loadTVShows() {
        viewModelScope.launch {
            repository.loadTVShows()
//                .collectLatest {
//                when (it) {
//                    DataResult.Error -> _uiState.value = MoviesViewState.Error
//                    DataResult.Loading -> _uiState.value = MoviesViewState.Loading
//                    DataResult.Success -> _uiState.value = MoviesViewState.Success
//                }
//            }
        }
    }

}