package com.wasif.topheadlines.utills

import com.wasif.topheadlines.data.models.ArticlesItem
import com.wasif.topheadlines.data.models.TopHeadlines

val mockResponse = TopHeadlines(
    status = "ok",
    totalResults = 1,
    articles = listOf(ArticlesItem(title = "Test headline"))
)
