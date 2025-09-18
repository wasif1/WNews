package com.wasif.countries.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.di.Qualifiers
import com.wasif.core.utills.ViewModelFactory
import com.wasif.countries.presentation.viewmodel.CountriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Qualifiers.ViewModelKey(CountriesViewModel::class)
    abstract fun bindNewsSourcesViewModel(viewModel: CountriesViewModel): ViewModel
}
