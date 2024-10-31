package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code

import kotlinx.coroutines.flow.Flow

interface ISetTimerFieldInteractor {
    suspend fun observeTimerFieldFlow(getCodeAfter: String, getNewCode: String): Flow<String>
    fun observeTimerStatusFlow(): Flow<Boolean>
}