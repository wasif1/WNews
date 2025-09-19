package com.wasif.languages.data.repository.sources

import com.wasif.languages.data.models.Language

interface DataSource {
    suspend fun getLanguages(): List<Language>
    fun priority(): Int
}