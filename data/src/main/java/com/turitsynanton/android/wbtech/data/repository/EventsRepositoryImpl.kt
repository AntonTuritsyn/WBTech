package com.turitsynanton.android.wbtech.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generatedAllEventsList
import com.turitsynanton.android.wbtech.data.mocks.generatedProfile
import com.turitsynanton.android.wbtech.data.mocks.myProfile
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.EventMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.UsersMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapEventToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapUserToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class EventRepositoryImpl(
    private val eventMapper: EventMapperToDomain,
    private val userMapper: UsersMapperToDomain
) : EventRepository {

    override fun getEventsListFlow(): Flow<List<DomainEvent>> =
        flow { emit(generatedAllEventsList) }.map { it.mapEventToDomain(eventMapper) }

    override fun getEventDetailsFlow(eventId: String): Flow<DomainEvent> =
        flow {
            generatedAllEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)
                ?.let { emit(it) }
        }

    override fun getMyProfileFlow(): Flow<DomainUser> {
        return flow { emit(myProfile) }.map { it.mapUserToDomain(userMapper) }
    }
}