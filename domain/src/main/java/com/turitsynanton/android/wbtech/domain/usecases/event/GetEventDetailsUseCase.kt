package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventDetailsUseCase(private val eventRepository: EventRepository) :
    IGetEventDetailsUseCase {
    override fun execute(eventId: String): Flow<DomainEvent> =
        eventRepository.getEventDetailsFlow(eventId)
}