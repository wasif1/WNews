package com.wasif.countries.data.repository

import com.wasif.core.di.Scopes
import com.wasif.countries.data.models.Country
import com.wasif.countries.data.repository.sources.DataSource
import com.wasif.countries.domain.repository.CountryRepository
import javax.inject.Inject

@Scopes.ActivityScope
class CountryRepositoryImp @Inject constructor(
    private val dataSources: Set<@JvmSuppressWildcards DataSource>
) : CountryRepository {

    override suspend fun getCountries(): List<Country> {
        return dataSources
            .sortedBy { it.priority() } // JSON -> DB -> API
            .firstNotNullOfOrNull { source ->
                runCatching { source.getCountries() }
                    .getOrNull()
                    ?.takeIf { it.isNotEmpty() }
            } ?: emptyList()
    }

}