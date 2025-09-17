package com.wasif.countries.data.network

import com.wasif.core.utills.Constants.Companion.NEWS_SOURCES
import com.wasif.countries.data.models.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApiService {

    @GET(NEWS_SOURCES)
    suspend fun getCountries(
        @Query("apiKey") apiKey: String
    ): List<Country>

}