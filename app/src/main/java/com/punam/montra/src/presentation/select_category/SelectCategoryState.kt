package com.punam.montra.src.presentation.select_category

import com.punam.montra.src.domain.model.response.CategoryResponse

data class SelectCategoryState(
    val categories: List<CategoryResponse> = emptyList(),
    val categoriesSelected: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isGettingMore: Boolean = false,
)