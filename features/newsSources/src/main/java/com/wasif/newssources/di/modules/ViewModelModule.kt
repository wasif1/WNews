package com.wasif.newssources.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.di.Qualifiers
import com.wasif.core.utills.ViewModelFactory
import com.wasif.newssources.presentation.viewmodel.NewsSourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Qualifiers.ViewModelKey(NewsSourcesViewModel::class)
    abstract fun bindNewsSourcesViewModel(viewModel: NewsSourcesViewModel): ViewModel
}
