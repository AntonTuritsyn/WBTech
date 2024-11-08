package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class DisableButtonForPastEventUseCase(
    private val eventRepository: EventRepository
): IDisableButtonForPastEventUseCase {
    @Suppress("NewApi")
    override fun execute(eventId: String): Flow<Boolean> {
        val eventDetails = eventRepository.getEventDetailsFlow(eventId)
        val button  = MutableStateFlow<Boolean>(false)

        val currentDate = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        eventDetails.map {
            val eventDate = LocalDate.parse(it.date, dateFormatter)
            if (eventDate.isBefore(currentDate)) {
                button.update {
                    println("true: ${true}")
                    true
                }
            } else {
                button.update {
                    println("false: ${false}")
                    false
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return button
    }
}