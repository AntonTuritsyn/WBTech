package com.turitsynanton.android.wbtech.domain.newusecases

import kotlinx.coroutines.flow.Flow

interface IGetSeachFieldTextUseCase {
    fun execute(): Flow<String>
}