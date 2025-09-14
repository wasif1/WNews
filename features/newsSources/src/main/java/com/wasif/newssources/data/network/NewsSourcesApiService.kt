package com.wasif.newssources.data.network

import com.wasif.core.utills.Constants.Companion.NEWS_SOURCES
import com.wasif.newssources.data.models.NewsSources
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsSourcesApiService {

    @GET(NEWS_SOURCES)
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String
    ): NewsSources

}