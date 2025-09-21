package com.wasif.search.domain.usecases

import com.wasif.core.utills.Resource
import com.wasif.search.data.models.SearchModel
import com.wasif.search.data.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<SearchModel>>> {
        return repository.getSearch(query)
    }
}
