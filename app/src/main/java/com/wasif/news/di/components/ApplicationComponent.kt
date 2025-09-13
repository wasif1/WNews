package com.wasif.news.di.components

import android.content.Context
import com.wasif.news.NewsApplication
import com.wasif.news.di.modules.ApplicationModule
import com.wasif.newsapp.di.Qualifiers.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: NewsApplication)

    @ApplicationContext
    fun applicationContext(): Context
}