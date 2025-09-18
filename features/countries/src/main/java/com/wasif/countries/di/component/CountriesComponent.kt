package com.wasif.countries.di.component

import android.content.Context
import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.countries.di.module.CountriesModule
import com.wasif.countries.di.module.DataSourceModule
import com.wasif.countries.di.module.RepositoryModule
import com.wasif.countries.di.module.ViewModelModule
import com.wasif.countries.presentation.ui.CountriesActivity
import dagger.BindsInstance
import dagger.Component

@Scopes.ActivityScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        CountriesModule::class,
        ViewModelModule::class,
    ]
)
interface CountriesComponent {
    fun inject(activity: CountriesActivity)
}