package com.wasif.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.search.data.models.SearchModel
import com.wasif.search.domain.usecases.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Scopes.ActivityScope
class SearchViewModel @Inject constructor(
    private val countriesUseCase: SearchUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val searchResults: StateFlow<Resource<SearchModel>> =
        _query
            .debounce(500)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .flatMapLatest { q ->
                countriesUseCase(q)
            }.stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                Resource.Loading
            )

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }
}
