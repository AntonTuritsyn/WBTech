package com.turitsynanton.android.wbtech.uinew.newviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenEventDetailsViewModel(
    eventId: String,
    private val iGetEventDetailsUseCase: IGetEventDetailsUseCase
): ViewModel() {
    private val _events: MutableStateFlow<DomainEvent?> = MutableStateFlow(null)
    private val events: StateFlow<DomainEvent?> = _events.asStateFlow()

    init {
        getEventDetails(eventId)
    }

    fun getEventDetailsFlow(): StateFlow<DomainEvent?> = events

    fun getEventDetails(eventsId: String) {
        viewModelScope.launch {
            iGetEventDetailsUseCase.execute(eventsId).collect { meetingDetails ->
                _events.update { meetingDetails }
            }
        }
    }
}