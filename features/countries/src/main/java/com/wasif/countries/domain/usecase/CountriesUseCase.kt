package com.wasif.countries.domain.usecase

import com.wasif.core.utills.Resource
import com.wasif.countries.data.models.Country
import com.wasif.countries.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> {
        return repository.getCountries()
    }
}
