package com.wasif.topheadlines.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.di.Qualifiers
import com.wasif.topheadlines.presentation.viewmodel.TopHeadlinesViewModel
import com.wasif.topheadlines.utills.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Qualifiers.ViewModelKey(TopHeadlinesViewModel::class)
    abstract fun bindTopHeadlinesViewModel(viewModel: TopHeadlinesViewModel): ViewModel
}
