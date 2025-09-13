package com.wasif.topheadlines.di.module

import android.app.Activity
import com.wasif.core.di.Scopes
import com.wasif.topheadlines.presentation.ui.TopHeadlinesActivity
import dagger.Module
import dagger.Provides

@Module
class TopHeadlineModule(private val activity: TopHeadlinesActivity) {

    @Provides
    @Scopes.ActivityScope
    fun provideActivity(): Activity {
        return activity
    }
}