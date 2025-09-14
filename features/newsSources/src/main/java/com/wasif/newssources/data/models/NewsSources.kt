package com.wasif.newssources.data.models

import com.google.gson.annotations.SerializedName

data class NewsSources(

	@field:SerializedName("sources")
	val sources: List<SourcesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)