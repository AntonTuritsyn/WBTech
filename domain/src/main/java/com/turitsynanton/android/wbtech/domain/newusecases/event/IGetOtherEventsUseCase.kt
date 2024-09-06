package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetOtherEventsUseCase {
    fun execute(eventId: String): Flow<List<DomainEvent>>
}