package com.wasif.languages.domain.usecase

import com.wasif.core.utills.Resource
import com.wasif.languages.data.models.Language
import com.wasif.languages.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguagesUseCase @Inject constructor(
    private val repository: LanguageRepository
) {
    operator fun invoke(): Flow<Resource<List<Language>>> {
        return repository.getLanguages()
    }
}
