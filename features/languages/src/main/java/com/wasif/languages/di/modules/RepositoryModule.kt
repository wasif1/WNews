package com.wasif.languages.di.modules

import com.wasif.languages.data.repository.LanguageRepositoryImp
import com.wasif.languages.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryRepository(
        impl: LanguageRepositoryImp
    ): LanguageRepository
}
