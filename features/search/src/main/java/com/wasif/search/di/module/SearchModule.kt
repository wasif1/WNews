package com.wasif.search.di.module

import com.wasif.search.data.network.SearchApiService
import com.wasif.search.data.repository.SearchRepository
import com.wasif.search.domain.usecases.SearchUseCase
import com.wasif.search.presentation.ui.SearchActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchModule(val activity: SearchActivity) {

    @Provides
    fun providesActivity(): SearchActivity = activity

    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    fun provideSearchRepository(apiService: SearchApiService): SearchRepository {
        return SearchRepository(apiService)
    }

    @Provides
    fun provideSearchUseCase(repository: SearchRepository) : SearchUseCase {
        return SearchUseCase(repository)
    }
}