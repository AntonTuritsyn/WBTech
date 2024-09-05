package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventListUseCase(private val dataListsRepository: IDataListsRepository) :
    IGetEventListUseCase {
    override fun execute(): Flow<List<DomainEvent>> = dataListsRepository.getEventsListFlow()
}