package com.wasif.languages.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.di.Qualifiers
import com.wasif.core.utills.ViewModelFactory
import com.wasif.languages.presentation.viewmodel.LanguagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Qualifiers.ViewModelKey(LanguagesViewModel::class)
    abstract fun bindNewsSourcesViewModel(viewModel: LanguagesViewModel): ViewModel
}
