package com.wasif.newssources.di.modules

import com.wasif.core.di.Scopes
import com.wasif.core.utills.dispatcher.DefaultDispatcher
import com.wasif.core.utills.dispatcher.DispatcherProvider
import com.wasif.newssources.data.network.NewsSourcesApiService
import com.wasif.newssources.data.repository.NewsSourcesRepository
import com.wasif.newssources.domain.NewsSourcesUseCase
import com.wasif.newssources.presentation.ui.NewsSourcesActivity
import com.wasif.newssources.presentation.viewmodel.NewsSourcesViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NewsSourcesModule(private val activity: NewsSourcesActivity) {

    @Provides
    @Scopes.ActivityScope
    fun provideActivity() = activity

    @Provides
    @Scopes.ActivityScope
    fun provideNewsSourcesApiService(retrofit: Retrofit) : NewsSourcesApiService {
        return retrofit.create(NewsSourcesApiService::class.java)
    }

    @Provides
    @Scopes.ActivityScope
    fun provideNewsSourcesRepository(retrofit: Retrofit) : NewsSourcesRepository {
        return NewsSourcesRepository(provideNewsSourcesApiService(retrofit))
    }

    @Provides
    @Scopes.ActivityScope
    fun provideNewsSourcesUseCase(retrofit: Retrofit):NewsSourcesUseCase {
        return NewsSourcesUseCase(provideNewsSourcesRepository(retrofit))
    }

    @Provides
    @Scopes.ActivityScope
    fun provideNewsSourcesViewModel(retrofit: Retrofit) : NewsSourcesViewModel {
        return NewsSourcesViewModel(provideNewsSourcesUseCase(retrofit), DefaultDispatcher())
    }
}