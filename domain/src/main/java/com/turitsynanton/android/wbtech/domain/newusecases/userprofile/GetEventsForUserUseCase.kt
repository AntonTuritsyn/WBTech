package com.turitsynanton.android.wbtech.domain.newusecases.userprofile

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetEventsForUserUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetEventsForUserUseCase {
    override fun execute(userId: String): Flow<List<DomainEvent>> {
        val events = dataListsRepository.getEventsListFlow()
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