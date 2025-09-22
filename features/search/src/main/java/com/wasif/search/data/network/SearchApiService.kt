package com.wasif.search.data.network

import com.wasif.core.utills.Constants.Companion.SEARCH
import com.wasif.search.data.models.SearchModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET(SEARCH)
    suspend fun getSearch(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String,
    ): List<SearchModel>

}