package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.newrepository.EventRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventDetailsUseCase(private val /*eventRepository: EventRepository*/dataListsRepository: DataListsRepository) :
    IGetEventDetailsUseCase {
    override fun execute(eventId: String): Flow<DomainEvent> =
        dataListsRepository.getEventDetailsFlow(eventId)
}