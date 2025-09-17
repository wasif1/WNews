package com.wasif.countries.di.module

import com.wasif.core.di.Scopes
import com.wasif.countries.data.network.CountriesApiService
import com.wasif.countries.data.repository.CountryRepositoryImp
import com.wasif.countries.data.repository.sources.DataSource
import com.wasif.countries.data.repository.sources.LocalJsonDataImp
import com.wasif.countries.data.repository.sources.NetworkDataImp
import com.wasif.countries.presentation.ui.CountriesActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CountriesModule(private val activity: CountriesActivity) {

    @Provides
    fun provideActivity(): CountriesActivity = activity

    @Provides
    @Scopes.ActivityScope
    fun provideCountriesApiService(retrofit: Retrofit): CountriesApiService {
        return retrofit.create(CountriesApiService::class.java)
    }

    @Provides
    @Scopes.ActivityScope
    fun provideCountriesLocalJson(): LocalJsonDataImp {
        return LocalJsonDataImp()
    }

    @Provides
    @Scopes.ActivityScope
    fun provideNetworkDataImp(countriesApiService: CountriesApiService): NetworkDataImp {
        return NetworkDataImp(countriesApiService)
    }

    @Provides
    @Scopes.ActivityScope
    fun provideCountryRepository(
        dataSources: Set<@JvmSuppressWildcards DataSource>
    ): CountryRepositoryImp {
        return CountryRepositoryImp(dataSources)
    }

}