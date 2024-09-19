package com.turitsynanton.android.wbtech.domain.usecases

import kotlinx.coroutines.flow.Flow

interface IGetSeachFieldTextUseCase {
    fun execute(): Flow<String>
}