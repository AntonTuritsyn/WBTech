package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update

internal class SetTimerFieldInteractor : ISetTimerFieldInteractor {

    private val startTime = 10
    private val endTime = 1

    private val timerStatus = MutableStateFlow(false)

    override suspend fun observeTimerFieldFlow(
        getCodeAfter: String,
        getNewCode: String
    ): Flow<String> {
        val timerField: Flow<String> = flow {
            for (step in startTime downTo endTime) {
                timerStatus.update {
                    false
                }
                emit("$getCodeAfter $step")
                delay(1000L)
            }
            emit(getNewCode)
            timerStatus.update { true }
        }
        return timerField
    }

    override fun observeTimerStatusFlow(): Flow<Boolean> {
        return timerStatus
    }
}