package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEventsListFlow(): Flow<List<DomainEvent>>

    fun getEventDetailsFlow(eventId: String): Flow<DomainEvent>
}