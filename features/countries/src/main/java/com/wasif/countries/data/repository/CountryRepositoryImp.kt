package com.wasif.countries.data.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.countries.data.models.Country
import com.wasif.countries.data.repository.sources.DataSource
import com.wasif.countries.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImp @Inject constructor(
    private val dataSources: Set<@JvmSuppressWildcards DataSource>
) : CountryRepository {

    override suspend fun getCountries(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading)

        val result = dataSources
            .sortedBy { it.priority() }
            .firstNotNullOfOrNull { source ->
                runCatching { source.getCountries() }
                    .getOrNull()
                    ?.takeIf { it.isNotEmpty() }
            }

        if (result != null) {
            emit(Resource.Success(result))
        } else {
            emit(Resource.Error("No data available"))
        }
    }
}