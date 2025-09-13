package com.wasif.news.di.modules

import android.content.Context
import com.wasif.news.NewsApplication
import com.wasif.newsapp.di.Qualifiers
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: NewsApplication) {

    @Provides
    @Qualifiers.ApplicationContext
    fun provideContext(): Context = application

}