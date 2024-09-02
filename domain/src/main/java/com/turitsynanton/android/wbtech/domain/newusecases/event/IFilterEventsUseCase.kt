package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IFilterEventsUseCase {
    fun execute(query: String, eventList: List<DomainEvent>): Flow<List<DomainEvent>>
}