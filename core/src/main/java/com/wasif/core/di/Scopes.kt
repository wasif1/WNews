package com.wasif.core.di

import javax.inject.Scope

object Scopes {
    @Scope
    @Retention(AnnotationRetention.BINARY)
    annotation class ActivityScope
}