package com.turitsynanton.android.wbtech.domain.newusecases.event

import kotlinx.coroutines.flow.Flow

interface IDisableButtonForPastEventUseCase {
    fun execute(eventId: String): Flow<Boolean>
}