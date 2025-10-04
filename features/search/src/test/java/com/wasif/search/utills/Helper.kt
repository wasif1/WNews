package com.wasif.search.utills

import com.wasif.search.data.models.SearchModel
import com.wasif.search.data.models.ArticlesItem

val mockResponse = SearchModel(
    status = "ok",
    totalResults = 6,
    articles = listOf(
        ArticlesItem(title = "Search-1"),
        ArticlesItem(title = "Search-2"),
        ArticlesItem(title = "Search-3"),
        ArticlesItem(title = "Search-4"),
        ArticlesItem(title = "Search-5"),
        ArticlesItem(title = "Search-6"),
    )
)
