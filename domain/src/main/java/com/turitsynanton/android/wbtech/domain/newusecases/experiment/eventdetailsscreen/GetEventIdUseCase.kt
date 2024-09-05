package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetEventIdUseCase {
    private val eventIdFlow = MutableSharedFlow<String>()
    private var lastvalue: String? = null

    fun loadNewEvent(eventId: String) {
        lastvalue = eventId
        eventIdFlow.tryEmit(eventId)
    }
    fun observe(): Flow<String> = eventIdFlow
}

class GetEventDetailsUseCase2(
    private val getEventIdUseCase: GetEventIdUseCase
) {
    fun invoke(eventId: String) {
        getEventIdUseCase.loadNewEvent(eventId)
    }
}

class GetEventDetails(
    private val getEventIdUseCase: GetEventIdUseCase,
    private val dataListsRepository: IDataListsRepository,
) {
    private val details = getEventIdUseCase.observe().mapLatest { id ->
        dataListsRepository.getEventDetailsFlowNew(id)
    }
    fun invoke(): Flow<DomainEvent?> = details
}