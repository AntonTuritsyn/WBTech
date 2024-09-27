package com.turitsynanton.android.wbtech.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.EventMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapEventToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class EventRepositoryImpl(private val mapper: EventMapperToDomain) : EventRepository {

    val eventsList = generateEvents(10, 40)

    override fun getEventsListFlow(): Flow<List<DomainEvent>> =
        flow { /*emit(getEventsListFlow) }.map { it.mapEventToDomain(mapper)*/ }

    override fun getEventDetailsFlow(eventId: String): Flow<DomainEvent> =
        flow {
//            getEventsListFlow.find { it.id == eventId }?.mapEventToDomain(mapper)?.let { emit(it) }
        }
}