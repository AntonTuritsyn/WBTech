package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class GetUpcomingEventsUseCase(
    private val eventRepository: EventRepository
) : IGetUpcomingEventsUseCase {

    @Suppress("NewApi")
    override fun execute(): Flow<List<DomainEvent>> {
        val events = eventRepository.getEventsListFlow()
        val upComingEvents = MutableStateFlow<List<DomainEvent>>(emptyList())

        val currentDate = LocalDate.now()
        val twoWeeksFromNow = currentDate.plusWeeks(2)
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        events.mapLatest { eventsList ->
            println("eventsList: $eventsList")
            upComingEvents.update {
                eventsList.filter { event ->
                    val eventDate = LocalDate.parse(event.date, dateFormatter)
                    eventDate in currentDate..twoWeeksFromNow
                }.sortedBy { it.date }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

        return upComingEvents
    }
}