package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class IsRegisteredForEventUseCase(
    private val eventRepository: EventRepository
) : IIsRegisteredForEventUseCase {
    override fun execute(eventId: String): Flow<Boolean> {
        val eventDetails = eventRepository.getEventDetailsFlow(eventId)
        val profileId = eventRepository.getMyProfileFlow().map { it.id }
        val button = MutableStateFlow<Boolean>(false)

        eventDetails.mapLatest { event ->
            button.update {
                event.participants.any { it.id == profileId.first() }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return button
    }
}
