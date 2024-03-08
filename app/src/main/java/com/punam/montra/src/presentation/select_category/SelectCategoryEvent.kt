package com.punam.montra.src.presentation.select_category

sealed class SelectCategoryEvent {

    data object Refresh : SelectCategoryEvent()
    data object GetMore : SelectCategoryEvent()
    data class SelectCategory(val value: String) : SelectCategoryEvent()
    data class UnselectCategory(val value: String) : SelectCategoryEvent()
}