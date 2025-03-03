package com.punam.montra.src.presentation.select_category

sealed class SelectCategoryEvent {

    data object Refresh : SelectCategoryEvent()
    data object GetMore : SelectCategoryEvent()
    data class SetLastCategoriesSelected(val value: List<String>) : SelectCategoryEvent()

}