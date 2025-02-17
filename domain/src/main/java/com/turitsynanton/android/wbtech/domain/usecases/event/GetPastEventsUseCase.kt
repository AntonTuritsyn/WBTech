package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class GetPastEventsUseCase(
    private val eventRepository: EventRepository
): IGetPastEventsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Suppress("NewApi")
    override fun execute(): Flow<List<DomainEvent>> {
        val events = eventRepository.getEventsListFlow()
        val pastEvents  = MutableStateFlow<List<DomainEvent>>(emptyList())

        val currentDate = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        events.mapLatest { eventsList ->
            println("eventsList: $eventsList")
            pastEvents .update {
                eventsList.filter { event ->
                    val eventDate = LocalDate.parse(event.date, dateFormatter)
                    eventDate.isBefore(currentDate)
                }.sortedBy { it.date }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        println("pastEvents: ${pastEvents.value}")
        return pastEvents
    }
}