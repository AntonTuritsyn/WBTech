package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetEventsListUseCaseNew(
    private val dataListsRepository: IDataListsRepository
) {
    private val eventList = dataListsRepository.getEventsListFlow()

    operator fun invoke(): Flow<List<DomainEvent>> = eventList
}