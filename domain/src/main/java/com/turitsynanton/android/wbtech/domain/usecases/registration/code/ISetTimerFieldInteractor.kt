package com.turitsynanton.android.wbtech.domain.usecases.registration.code

import kotlinx.coroutines.flow.Flow

interface ISetTimerFieldInteractor {
    fun observeTimerFieldFlow(): Flow<String>
    fun observeTimerStatusFlow(): Flow<Boolean>
}