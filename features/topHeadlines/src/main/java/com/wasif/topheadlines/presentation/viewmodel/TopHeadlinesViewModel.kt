package com.wasif.topheadlines.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasif.core.data.models.UiState
import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.core.utills.dispatcher.DefaultDispatcher
import com.wasif.core.utills.dispatcher.DispatcherProvider
import com.wasif.topheadlines.data.models.ArticlesItem
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Scopes.ActivityScope
class TopHeadlinesViewModel @Inject constructor(
    private val topHeadlinesUseCase: TopHeadlinesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<ArticlesItem>>())
    val uiState: StateFlow<UiState<List<ArticlesItem>>> = _uiState.asStateFlow()

    fun fetchTopHeadlines(code: String) {
        viewModelScope.launch(dispatcherProvider.main()) {
            topHeadlinesUseCase(code).collect { resource ->
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
                            data = resource.data.articles ?: emptyList(),
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
