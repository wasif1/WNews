package com.wasif.topheadlines.domain

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.models.TopHeadlines
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Scopes.ActivityScope
class TopHeadlinesUseCase @Inject constructor(
    private val repository: TopHeadlinesRepository
) {
    operator fun invoke(code: String): Flow<Resource<TopHeadlines>> {
        return repository.getTopHeadlines(code)
    }
}
