package com.wasif.languages.domain.repository

import com.wasif.core.di.Scopes
import com.wasif.core.utills.Resource
import com.wasif.languages.data.models.Language
import kotlinx.coroutines.flow.Flow

@Scopes.ActivityScope
interface LanguageRepository {
    fun getLanguages(): Flow<Resource<List<Language>>>
}