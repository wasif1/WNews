package com.wasif.search.domain.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.search.data.models.SearchModel
import kotlinx.coroutines.flow.Flow

@Scopes.ActivityScope
interface SearchRepositoryImp {
    suspend fun getSearch(): Flow<Resource<List<SearchModel>>>
}