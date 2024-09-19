package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetEventDetailsUseCase {
    fun execute(eventId: String) : Flow<DomainEvent>
}