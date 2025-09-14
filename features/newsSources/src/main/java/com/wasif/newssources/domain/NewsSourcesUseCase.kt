package com.wasif.newssources.domain

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.newssources.data.models.NewsSources
import com.wasif.newssources.data.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Scopes.ActivityScope
class NewsSourcesUseCase @Inject constructor(
    private val repository: NewsSourcesRepository
) {
    operator fun invoke(): Flow<Resource<NewsSources>> {
        return repository.getNewsSources()
    }
}
