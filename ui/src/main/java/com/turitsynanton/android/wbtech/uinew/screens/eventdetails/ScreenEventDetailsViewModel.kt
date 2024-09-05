package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetails
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetailsUseCase2
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.IGetEventDetailsUseCaseNew
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenEventDetailsViewModel(
    eventId: String,
    private val iGetEventDetailsUseCase: IGetEventDetailsUseCase,
    private val getEventDetailsUseCaseNew: IGetEventDetailsUseCaseNew,
    private val getEventIdUseCase: GetEventIdUseCase,
    private val getEventDetails: GetEventDetails,
    private val getEventDetailsUseCase2: GetEventDetailsUseCase2
) : ViewModel() {
    private val _events: MutableStateFlow<DomainEvent?> = MutableStateFlow(null)
    private val events: StateFlow<DomainEvent?> = _events.asStateFlow()

    init {
        getEventDetails(eventId)
    }

    fun getEventDetailsFlow(): StateFlow<DomainEvent?> = events

    fun getEventDetails(eventsId: String) {
        viewModelScope.launch {
//            getEventDetails.invoke()
            iGetEventDetailsUseCase.execute(eventsId).collect { eventDetails ->
                _events.update { eventDetails }
            }
        }
    }
}