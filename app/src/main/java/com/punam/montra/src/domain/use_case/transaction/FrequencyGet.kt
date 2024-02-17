package com.punam.montra.src.domain.use_case.transaction

import com.punam.montra.src.domain.model.response.FrequencyResponse
import com.punam.montra.src.domain.repository.TransactionRepository
import com.punam.montra.util.CategoryType
import com.punam.montra.util.FrequencyType
import com.punam.montra.util.ViewState

class FrequencyGet(
    private val repository: TransactionRepository,
) {
    suspend operator fun invoke(
        userId: String,
        frequencyType: FrequencyType,
        categoryType: CategoryType,
        timeZone: Int
    ): ViewState<FrequencyResponse> {
        val res = repository.getFrequency(
            userId = userId,
            frequencyType = frequencyType,
            categoryType = categoryType,
            timeZone = timeZone
        )
        return res.fold(
            { ViewState.Error(it) },
            { ViewState.Success(it) }
        )
    }
}