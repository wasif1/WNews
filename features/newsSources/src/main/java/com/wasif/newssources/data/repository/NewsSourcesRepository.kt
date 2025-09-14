package com.wasif.newssources.data.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Resource
import com.wasif.newssources.data.models.NewsSources
import com.wasif.newssources.data.network.NewsSourcesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Scopes.ActivityScope
class NewsSourcesRepository @Inject constructor(
    private val newSourcesApiService: NewsSourcesApiService
) {
    fun getNewsSources(): Flow<Resource<NewsSources>> = flow {
        try {
            emit(Resource.Loading)
            val response =
                newSourcesApiService.getNewsSources(apiKey = API_KEY)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
