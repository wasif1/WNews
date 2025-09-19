package com.wasif.languages.di.components

import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.languages.di.modules.DataSourceModule
import com.wasif.languages.di.modules.LanguageModule
import com.wasif.languages.di.modules.RepositoryModule
import com.wasif.languages.di.modules.ViewModelModule
import com.wasif.languages.presentation.ui.LanguagesActivity
import dagger.Component


@Scopes.ActivityScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [LanguageModule::class, DataSourceModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface LanguageComponent {
    fun inject(activity: LanguagesActivity)
}