package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.eventlist

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventsListUseCaseNew(
    private val dataListsRepository: IDataListsRepository
) {
    private val eventList = dataListsRepository.getEventsListFlow()

    operator fun invoke(): Flow<List<DomainEvent>> = eventList
}