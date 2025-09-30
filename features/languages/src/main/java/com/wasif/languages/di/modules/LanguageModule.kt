package com.wasif.languages.di.modules

import android.content.Context
import com.wasif.core.utills.dispatcher.DefaultDispatcher
import com.wasif.core.utills.dispatcher.DispatcherProvider
import com.wasif.languages.domain.repository.LanguageRepository
import com.wasif.languages.domain.usecase.LanguagesUseCase
import com.wasif.languages.presentation.ui.LanguagesActivity
import dagger.Module
import dagger.Provides

@Module
class LanguageModule(private val activity: LanguagesActivity) {

    @Provides
    fun provideActivity(): LanguagesActivity = activity

    @Provides
    fun provideContext(): Context = activity.baseContext


    @Provides
    fun provideGetCountriesUseCase(languageRepository: LanguageRepository): LanguagesUseCase {
        return LanguagesUseCase(languageRepository)
    }

    @Provides
    fun provideDefaultDispatcher(): DispatcherProvider {
        return DefaultDispatcher()
    }

}