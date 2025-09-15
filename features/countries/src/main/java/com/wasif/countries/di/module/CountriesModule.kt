package com.wasif.countries.di.module

import com.wasif.countries.presentation.ui.CountriesActivity
import dagger.Module
import dagger.Provides

@Module
class CountriesModule(private val activity: CountriesActivity) {

    @Provides
    fun provideActivity(): CountriesActivity = activity
}