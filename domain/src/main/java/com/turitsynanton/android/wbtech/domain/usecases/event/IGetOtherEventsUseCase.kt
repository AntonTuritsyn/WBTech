package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetOtherEventsUseCase {
    fun execute(eventId: String): Flow<List<DomainEvent>>
}