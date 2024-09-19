package com.turitsynanton.android.wbtech.domain.usecases.event.filter

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IFilterEventsUseCase {
    fun execute(query: String): Flow<List<DomainEvent>>
}