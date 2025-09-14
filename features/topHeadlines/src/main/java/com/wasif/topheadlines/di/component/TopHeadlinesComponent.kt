package com.wasif.topheadlines.di.component

import com.wasif.core.di.Scopes
import com.wasif.core.di.components.CoreComponent
import com.wasif.topheadlines.di.module.TopHeadlineModule
import com.wasif.topheadlines.di.module.ViewModelModule
import com.wasif.topheadlines.presentation.ui.TopHeadlinesActivity
import dagger.Component

@Scopes.ActivityScope
@Component(dependencies = [CoreComponent::class], modules = [TopHeadlineModule::class, ViewModelModule::class])
interface TopHeadlinesComponent {
    fun inject(activity: TopHeadlinesActivity)
}