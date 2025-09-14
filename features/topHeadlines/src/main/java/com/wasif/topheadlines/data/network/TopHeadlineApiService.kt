package com.wasif.topheadlines.data.network

import com.wasif.core.utills.Constants.Companion.TOP_HEADLINES
import com.wasif.topheadlines.data.models.TopHeadlines
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlineApiService {

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlines

}