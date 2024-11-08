package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.eventlist

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventsListUseCaseNew(
    private val eventRepository: EventRepository
) {
    private val eventList = eventRepository.getEventsListFlow()

    operator fun invoke(): Flow<List<DomainEvent>> = eventList
}