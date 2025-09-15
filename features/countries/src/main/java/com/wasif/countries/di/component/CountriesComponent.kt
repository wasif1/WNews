package com.wasif.countries.di.component

import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.countries.di.module.CountriesModule
import com.wasif.countries.presentation.ui.CountriesActivity
import dagger.Component

@Scopes.ActivityScope
@Component(dependencies = [CoreComponent::class], modules = [CountriesModule::class])
interface CountriesComponent {
    fun inject(activity: CountriesActivity)
}