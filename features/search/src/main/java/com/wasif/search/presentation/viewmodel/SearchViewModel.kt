package com.wasif.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasif.core.data.models.UiState
import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.search.data.models.SearchModel
import com.wasif.search.domain.usecases.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Scopes.ActivityScope
class SearchViewModel @Inject constructor(
    private val countriesUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<SearchModel>>())
    val uiState: StateFlow<UiState<List<SearchModel>>> = _uiState.asStateFlow()

    fun fetchSearchResult(query: String) {
        viewModelScope.launch {
            countriesUseCase(query).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            data = resource.data,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                    }
                }
            }
        }
    }
}
