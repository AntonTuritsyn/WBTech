package com.turitsynanton.android.wbtech.ui.screens.registrationend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import com.turitsynanton.android.wbtech.ui.utils.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenFinishRegistrationViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val resourceProvider: ResourceProvider,
    private val getEventDetailsUseCase: IGetEventDetailsUseCase
) : ViewModel() {
    private val _eventDetails: MutableStateFlow<UiEventCard?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEventCard?> = _eventDetails.asStateFlow()

    init {
        getEventDetails(eventId)
    }

    fun getEventDetailsFlow(): StateFlow<UiEventCard?> = eventDetails

    private fun getEventDetails(eventId: String) {
        viewModelScope.launch {
            getEventDetailsUseCase.execute(eventId).collect { event ->
                _eventDetails.update { event.mapEventCardToUi(eventCardMapper) }
            }
        }
    }
}