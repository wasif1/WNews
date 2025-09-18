package com.wasif.countries.di.module

import com.wasif.countries.data.repository.CountryRepositoryImp
import com.wasif.countries.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryRepository(
        impl: CountryRepositoryImp
    ): CountryRepository
}
