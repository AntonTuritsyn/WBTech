package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventListUseCase(private val dataListsRepository: IDataListsRepository) :
    IGetEventListUseCase {
    override fun execute(): Flow<List<DomainEvent>> = dataListsRepository.getEventsListFlow()
}