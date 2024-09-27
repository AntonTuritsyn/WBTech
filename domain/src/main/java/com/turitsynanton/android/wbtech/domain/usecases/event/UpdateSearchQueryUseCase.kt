package com.turitsynanton.android.wbtech.domain.usecases.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class UpdateSearchQueryUseCase: IUpdateSearchQueryUseCase {
    override fun execute(query: String): Flow<String> = MutableStateFlow(query)
}