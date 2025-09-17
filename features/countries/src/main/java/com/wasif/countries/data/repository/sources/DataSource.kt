package com.wasif.countries.data.repository.sources

import com.wasif.countries.data.models.Country

interface DataSource {
    suspend fun getCountries(): List<Country>
    fun priority(): Int // lower number = higher priority
}