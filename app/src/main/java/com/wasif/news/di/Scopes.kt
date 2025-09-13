package com.wasif.news.di

import javax.inject.Scope

object Scopes {
    @Scope
    @Retention(AnnotationRetention.BINARY)
    annotation class ActivityScope
}