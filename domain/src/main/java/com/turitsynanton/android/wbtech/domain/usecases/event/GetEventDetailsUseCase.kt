package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventDetailsUseCase(private val dataListsRepository: DataListsRepository) :
    IGetEventDetailsUseCase {
    override fun execute(eventId: String): Flow<DomainEvent> =
        dataListsRepository.getEventDetailsFlow(eventId)
}