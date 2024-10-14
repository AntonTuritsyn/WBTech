package com.turitsynanton.android.wbtech.uinew.screens.registrationend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registration.ISetRegistrationStepUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registration.code.ISetCodeQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registration.code.ISetTimerFieldInteractor
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import com.turitsynanton.android.wbtech.uinew.utils.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenFinishRegistrationViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val resourceProvider: ResourceProvider,
    private val getEventDetailsUseCase: IGetEventDetailsUseCase,
    private val setRegistrationStep: ISetRegistrationStepUseCase,
    private val setTimerField: ISetTimerFieldInteractor,
    private val setCodeQuery: ISetCodeQueryInteractor
): ViewModel() {
    private val _eventDetails: MutableStateFlow<UiEventCard?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEventCard?> = _eventDetails.asStateFlow()

    fun getEventDetailsFlow(): StateFlow<UiEventCard?> = eventDetails

    init {
        getEventDetails(eventId)
    }

    private fun getEventDetails(eventId: String) {
        viewModelScope.launch {
            getEventDetailsUseCase.execute(eventId).collect { event ->
                _eventDetails.update { event.mapEventCardToUi(eventCardMapper) }
            }
        }
    }
}