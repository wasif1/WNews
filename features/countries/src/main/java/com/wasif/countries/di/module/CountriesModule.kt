package com.wasif.countries.di.module

import android.content.Context
import com.wasif.countries.data.network.CountriesApiService
import com.wasif.countries.domain.repository.CountryRepository
import com.wasif.countries.domain.usecase.GetCountriesUseCase
import com.wasif.countries.presentation.ui.CountriesActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CountriesModule(private val activity: CountriesActivity) {

    @Provides
    fun provideActivity(): CountriesActivity = activity

    @Provides
    fun provideContext(): Context = activity.baseContext

    @Provides
    fun provideCountriesApiService(retrofit: Retrofit): CountriesApiService {
        return retrofit.create(CountriesApiService::class.java)
    }

    @Provides
    fun provideGetCountriesUseCase(countryRepository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countryRepository)
    }

}