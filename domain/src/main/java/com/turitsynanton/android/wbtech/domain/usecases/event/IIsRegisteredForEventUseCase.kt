package com.turitsynanton.android.wbtech.domain.usecases.event

import kotlinx.coroutines.flow.Flow

interface IIsRegisteredForEventUseCase {
    fun execute(eventId: String): Flow<Boolean>
}