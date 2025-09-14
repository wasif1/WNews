package com.wasif.topheadlines.di.module

import android.app.Activity
import com.wasif.core.di.Scopes
import com.wasif.topheadlines.data.network.TopHeadlineApiService
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import com.wasif.topheadlines.presentation.ui.TopHeadlinesActivity
import com.wasif.topheadlines.presentation.viewmodel.TopHeadlinesViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TopHeadlineModule(private val activity: TopHeadlinesActivity) {

    @Provides
    @Scopes.ActivityScope
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @Singleton
    fun provideTopHeadlinesApiService(retrofit: Retrofit): TopHeadlineApiService {
        return retrofit.create(TopHeadlineApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTopHeadlinesRepository(retrofit: Retrofit): TopHeadlinesRepository {
        return TopHeadlinesRepository(provideTopHeadlinesApiService(retrofit))
    }

    @Provides
    @Singleton
    fun provideTopHeadlinesUseCase(retrofit: Retrofit): TopHeadlinesUseCase {
        return TopHeadlinesUseCase(provideTopHeadlinesRepository(retrofit))
    }

    @Provides
    @Scopes.ActivityScope
    fun provideTopHeadlinesViewModel(retrofit: Retrofit): TopHeadlinesViewModel {
        return TopHeadlinesViewModel(provideTopHeadlinesUseCase(retrofit))
    }
}