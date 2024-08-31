package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.newrepository.EventRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventListUseCase(private val /*eventRepository: EventRepository*/dataListsRepository: DataListsRepository) :
    IGetEventListUseCase {
    override fun execute(): Flow<List<DomainEvent>> = dataListsRepository.getEventsListFlow()
}