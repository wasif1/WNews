package com.wasif.languages.data.repository

import com.wasif.core.utills.Resource
import com.wasif.languages.data.models.Language
import com.wasif.languages.data.repository.sources.DataSource
import com.wasif.languages.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LanguageRepositoryImp @Inject constructor(
    private val dataSources: Set<@JvmSuppressWildcards DataSource>
) : LanguageRepository {

    override suspend fun getLanguages(): Flow<Resource<List<Language>>> = flow {
        emit(Resource.Loading)

        val result = dataSources.sortedBy { it.priority() }.firstNotNullOfOrNull { source ->
            runCatching { source.getLanguages() }.getOrNull()?.takeIf { it.isNotEmpty() }
        }

        if (result != null) {
            emit(Resource.Success(result))
        } else {
            emit(Resource.Error("No data available"))
        }
    }
}