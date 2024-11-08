package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

internal class GetEventsForUserUseCase(
    private val eventRepository: EventRepository
): IGetEventsForUserUseCase {
    override fun execute(userId: String): Flow<List<DomainEvent>> {
        val events = eventRepository.getEventsListFlow()
        val eventsForUser = MutableStateFlow<List<DomainEvent>>(emptyList())

        events.map { eventsList->
            val filteredEvents = eventsList.filter { event->
                event.participants.any { participants->
                    participants.id == userId
                }
            }
            println("filteredEvents: $filteredEvents")
            eventsForUser.update { filteredEvents }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return eventsForUser
    }
}