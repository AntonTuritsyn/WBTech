package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventListUseCase(private val eventRepository: EventRepository) :
    IGetEventListUseCase {
    override fun execute(): Flow<List<DomainEvent>> = eventRepository.getEventsListFlow()
}