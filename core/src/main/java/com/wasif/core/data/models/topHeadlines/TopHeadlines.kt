package com.wasif.core.data.models.topHeadlines

import com.google.gson.annotations.SerializedName

data class TopHeadlines(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: ArrayList<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)