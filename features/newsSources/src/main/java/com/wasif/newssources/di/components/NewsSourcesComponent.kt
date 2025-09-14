package com.wasif.newssources.di.components

import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.newssources.di.modules.NewsSourcesModule
import com.wasif.newssources.di.modules.ViewModelModule
import com.wasif.newssources.presentation.ui.NewsSourcesActivity
import dagger.Component

@Scopes.ActivityScope
@Component(dependencies = [CoreComponent::class], modules = [NewsSourcesModule::class, ViewModelModule::class])
interface NewsSourcesComponent {
    fun inject(activity: NewsSourcesActivity)
}