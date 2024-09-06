package com.turitsynanton.android.wbtech.data.newrepository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapEventToDomain
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class EventRepositoryImpl(private val mapper: EventMapper) : EventRepository {

    val eventsList = generateEvents(10, 40)

    override fun getEventsList(): Flow<List<DomainEvent>> =
        flow { emit(eventsList) }.map { it.mapEventToDomain(mapper) }

    override fun getEventDetails(eventId: String): Flow<DomainEvent> =
        flow {
            eventsList.find { it.id == eventId }?.mapEventToDomain(mapper)?.let { emit(it) }
        }

    override fun goToEvent(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun cancelEvent(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}