package com.wasif.topheadlines.data.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.models.TopHeadlines
import com.wasif.topheadlines.data.network.TopHeadlineApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Scopes.ActivityScope
class TopHeadlinesRepository @Inject constructor(
    private val topHeadlineApiService: TopHeadlineApiService
) {
    fun getTopHeadlines(code: String): Flow<Resource<TopHeadlines>> = flow {
        try {
            emit(Resource.Loading)
            val response =
                topHeadlineApiService.getTopHeadlines(country = code, apiKey = API_KEY)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
