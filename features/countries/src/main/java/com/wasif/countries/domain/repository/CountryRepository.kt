package com.wasif.countries.domain.repository

import com.wasif.core.di.Scopes
import com.wasif.countries.data.models.Country

@Scopes.ActivityScope
interface CountryRepository {
    suspend fun getCountries(): List<Country>
}