package com.wasif.languages.di.modules

import com.wasif.languages.data.repository.sources.DataSource
import com.wasif.languages.data.repository.sources.LocalLanguageDataImp
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface DataSourceModule {

    @Binds
    @IntoSet
    fun bindLocalDataSource(
        impl: LocalLanguageDataImp
    ): DataSource
}
