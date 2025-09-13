package com.wasif.core

import android.app.Application
import com.wasif.core.di.components.CoreComponent
import com.wasif.core.di.components.DaggerCoreComponent
import com.wasif.core.di.modules.CoreModule


class NewsApplication : Application() {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        setDependencies()
    }

    private fun setDependencies() {
        coreComponent = DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
        coreComponent.inject(this)
    }
}