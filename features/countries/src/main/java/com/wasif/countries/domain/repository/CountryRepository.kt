package com.wasif.countries.domain.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.countries.data.models.Country
import kotlinx.coroutines.flow.Flow

@Scopes.ActivityScope
interface CountryRepository {
    fun getCountries(): Flow<Resource<List<Country>>>
}