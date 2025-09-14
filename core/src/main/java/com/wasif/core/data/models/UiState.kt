package com.wasif.core.data.models

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,  // nullable
    val error: String? = null
)