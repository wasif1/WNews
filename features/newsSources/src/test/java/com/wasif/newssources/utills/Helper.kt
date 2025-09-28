package com.wasif.newssources.utills

import com.wasif.newssources.data.models.NewsSources
import com.wasif.newssources.data.models.SourcesItem


val mockResponse = NewsSources(
    status = "ok",
    sources = listOf(SourcesItem(name = "Test Source"))
)
