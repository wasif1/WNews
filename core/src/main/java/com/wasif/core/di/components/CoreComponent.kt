package com.wasif.core.di.components

import android.content.Context
import com.wasif.core.NewsApplication
import com.wasif.core.di.Qualifiers
import com.wasif.core.di.modules.CoreModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun inject(application: NewsApplication)

    @Qualifiers.ApplicationContext
    fun applicationContext(): Context

    fun getRetrofit(): Retrofit
}