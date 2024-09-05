package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class InnerFilterEventUseCaseNew: IInnerFilterEventUseCaseNew {
    private val queryForSearch = MutableStateFlow<String>("")
    override fun execute(query: String) {
        queryForSearch.update {
            query
        }
    }
    fun observe(): Flow<String> = queryForSearch
}