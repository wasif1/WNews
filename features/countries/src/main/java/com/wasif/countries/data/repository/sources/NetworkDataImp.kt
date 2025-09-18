package com.wasif.countries.data.repository.sources

import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.countries.data.models.Country
import com.wasif.countries.data.network.CountriesApiService
import javax.inject.Inject


class NetworkDataImp @Inject constructor(private val countriesApiService: CountriesApiService) :
    DataSource {
    override suspend fun getCountries(): List<Country> =
        countriesApiService.getCountries(API_KEY)

    override fun priority(): Int {
        return 2
    }
}