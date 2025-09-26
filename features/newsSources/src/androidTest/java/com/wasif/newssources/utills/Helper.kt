package com.wasif.newssources.utills

import com.wasif.newssources.data.models.NewsSources
import com.wasif.newssources.data.models.SourcesItem


val mockResponse = NewsSources(
    status = "ok",
    sources = listOf(
        SourcesItem(name = "Test Source1"),
        SourcesItem(name = "Test Source2"),
        SourcesItem(name = "Test Source3"),
        SourcesItem(name = "Test Source4"),
        SourcesItem(name = "Test Source5"),
        SourcesItem(name = "Test Source6"),
        SourcesItem(name = "Test Source7"),
    )
)
