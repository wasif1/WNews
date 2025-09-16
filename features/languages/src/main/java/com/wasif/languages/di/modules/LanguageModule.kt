package com.wasif.languages.di.modules

import com.wasif.languages.presentation.ui.LanguagesActivity
import dagger.Module
import dagger.Provides

@Module
class LanguageModule(private val activity: LanguagesActivity) {

    @Provides
    fun provideActivity(): LanguagesActivity = activity
}