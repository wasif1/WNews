package com.wasif.core.di

import javax.inject.Qualifier

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
}