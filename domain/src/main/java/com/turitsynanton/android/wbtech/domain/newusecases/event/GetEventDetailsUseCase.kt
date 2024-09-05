package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventDetailsUseCase(private val dataListsRepository: IDataListsRepository) :
    IGetEventDetailsUseCase {
    override fun execute(eventId: String): Flow<DomainEvent> =
        dataListsRepository.getEventDetailsFlow(eventId)
}