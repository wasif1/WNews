package com.wasif.newssources.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasif.core.data.models.UiState
import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.newssources.data.models.SourcesItem
import com.wasif.newssources.domain.NewsSourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Scopes.ActivityScope
class NewsSourcesViewModel @Inject constructor(
    private val newsSourcesUseCase: NewsSourcesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<SourcesItem>>())
    val uiState: StateFlow<UiState<List<SourcesItem>>> = _uiState.asStateFlow()

    fun fetchNewsSources() {
        viewModelScope.launch {
            newsSourcesUseCase().collect { resource ->
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
                            data = resource.data.sources ?: emptyList(),
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
