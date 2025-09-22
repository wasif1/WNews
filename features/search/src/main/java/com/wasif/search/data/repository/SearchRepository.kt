package com.wasif.search.data.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Resource
import com.wasif.search.data.models.SearchModel
import com.wasif.search.data.network.SearchApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Scopes.ActivityScope
class SearchRepository @Inject constructor(
    private val searchApiService: SearchApiService
) {
    fun getSearch(query: String): Flow<Resource<SearchModel>> = flow {
        try {
            emit(Resource.Loading)
            val response =
                searchApiService.getSearch(query = query, apiKey = API_KEY)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
