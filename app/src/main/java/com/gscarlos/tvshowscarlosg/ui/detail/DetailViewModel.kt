package com.gscarlos.tvshowscarlosg.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscarlos.tvshowscarlosg.data.datasource.TVShowsRepository
import com.gscarlos.tvshowscarlosg.data.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TVShowsRepository
) : ViewModel() {

    private val _detailState = MutableStateFlow<DetailViewState>(DetailViewState.Start)
    val detailState = _detailState.asStateFlow()

    fun loadShow(idShow: String) {
        viewModelScope.launch {
            repository.detailTVShow(idShow).collectLatest {
                when (it) {
                    is DataResult.Error -> _detailState.value = DetailViewState.Error(it.errorType)
                    DataResult.Loading -> _detailState.value = DetailViewState.Loading
                    is DataResult.Success -> _detailState.value = DetailViewState.SuccessData(it.data)

                }
            }
        }
    }
}