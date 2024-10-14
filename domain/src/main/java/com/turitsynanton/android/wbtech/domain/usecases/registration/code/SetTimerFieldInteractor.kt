package com.turitsynanton.android.wbtech.domain.usecases.registration.code

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

    private val timerField = MutableStateFlow("")
    private val timerStatus = MutableStateFlow(false)
    private val observableTimerField = timerField.flatMapLatest {
        flow {
            for (step in startTime downTo endTime) {
//                TODO поменять на передачу строки
                emit("Получить новый код через $step")
                delay(1000L)
                timerStatus.update {
                    false
                }
            }
            emit("Получить новый код")
            timerStatus.update { true }
        }.flowOn(Dispatchers.IO)
    }

    override fun observeTimerFieldFlow(): Flow<String> {
        return observableTimerField
    }

    override fun observeTimerStatusFlow(): Flow<Boolean> {
        return timerStatus
    }
}