package com.wasif.search.di.component

import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.search.di.module.SearchModule
import com.wasif.search.di.module.ViewModelModule
import com.wasif.search.presentation.ui.SearchActivity
import dagger.Component


@Scopes.ActivityScope
@Component(modules = [SearchModule::class, ViewModelModule::class], dependencies = [CoreComponent::class])
interface SearchComponent {
    fun inject(activity: SearchActivity)
}