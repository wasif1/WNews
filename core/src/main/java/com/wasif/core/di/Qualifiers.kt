package com.wasif.core.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Qualifier
import kotlin.reflect.KClass

object Qualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApplicationContext

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ActivityContext

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

}