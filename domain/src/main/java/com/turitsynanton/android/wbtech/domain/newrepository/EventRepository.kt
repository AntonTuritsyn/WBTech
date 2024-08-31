package com.turitsynanton.android.wbtech.domain.newrepository

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEventsList(): Flow<List<DomainEvent>>

    fun getEventDetails(eventId: String): Flow<DomainEvent>

    fun goToEvent(): Flow<Boolean>

    fun cancelEvent(): Flow<Boolean>
}