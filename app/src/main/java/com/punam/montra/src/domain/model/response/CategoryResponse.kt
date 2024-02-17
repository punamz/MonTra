package com.punam.montra.src.domain.model.response

import com.punam.montra.util.CategoryType

data class CategoryResponse(
    val category: String,
    val color: String,
    val icon: String,
    val id: String,
    val type: CategoryType
)