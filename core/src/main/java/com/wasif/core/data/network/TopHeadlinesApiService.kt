package com.wasif.core.data.network

import com.wasif.core.data.models.topHeadlines.TopHeadlines

interface TopHeadlinesApiService {
    suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): TopHeadlines
}