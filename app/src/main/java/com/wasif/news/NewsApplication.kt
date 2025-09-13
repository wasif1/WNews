package com.wasif.news

import android.app.Application
import com.wasif.news.di.components.ApplicationComponent
import com.wasif.news.di.components.DaggerApplicationComponent
import com.wasif.news.di.modules.ApplicationModule


class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        setDependencies()
    }

    private fun setDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}