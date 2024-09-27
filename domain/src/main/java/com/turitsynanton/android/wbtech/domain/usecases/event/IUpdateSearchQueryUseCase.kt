package com.turitsynanton.android.wbtech.domain.usecases.event

import kotlinx.coroutines.flow.Flow

interface IUpdateSearchQueryUseCase {
    fun execute(query: String): Flow<String>
}