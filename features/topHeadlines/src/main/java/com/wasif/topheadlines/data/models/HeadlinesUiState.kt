package com.wasif.topheadlines.data.models

data class HeadlinesUiState(
    val isLoading: Boolean = false,
    val articles: List<ArticlesItem> = emptyList(),
    val error: String? = null
)
