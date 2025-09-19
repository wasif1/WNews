package com.wasif.languages.data.repository.sources

import android.content.Context
import com.wasif.languages.data.models.Language
import javax.inject.Inject


class LocalLanguageDataImp @Inject constructor(
    private val context: Context
) : DataSource {
    override suspend fun getLanguages(): List<Language> {
        return listOf(
            Language("English", "US"),
            Language("Portuguese", "BR"),
            Language("Arabic", "SA"),
            Language("Japanese", "JP"),
            Language("German", "DE"),
            Language("Urdu", "PK")
        )
    }

    override fun priority(): Int {
        return 1
    }
}