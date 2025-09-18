package com.wasif.countries.di.module

import com.wasif.countries.data.repository.sources.DataSource
import com.wasif.countries.data.repository.sources.LocalJsonDataImp
import com.wasif.countries.data.repository.sources.NetworkDataImp
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface DataSourceModule {

    @Binds
    @IntoSet
    fun bindRemoteDataSource(
        impl: NetworkDataImp
    ): DataSource

    @Binds
    @IntoSet
    fun bindLocalDataSource(
        impl: LocalJsonDataImp
    ): DataSource
}
