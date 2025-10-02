package com.wasif.countries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasif.core.data.models.UiState
import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.core.utills.dispatcher.DispatcherProvider
import com.wasif.countries.data.models.Country
import com.wasif.countries.domain.usecase.CountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Scopes.ActivityScope
class CountriesViewModel @Inject constructor(
    private val countriesUseCase: CountriesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<Country>>())
    val uiState: StateFlow<UiState<List<Country>>> = _uiState.asStateFlow()

    fun fetchCountries() {
        viewModelScope.launch(dispatcherProvider.main()) {
            countriesUseCase().collect { resource ->
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
