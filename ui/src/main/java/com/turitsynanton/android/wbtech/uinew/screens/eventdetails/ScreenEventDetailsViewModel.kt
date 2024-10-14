package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetVisitorsCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IDisableButtonForPastEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IIsRegisteredForEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import com.turitsynanton.android.wbtech.uinew.state.eventdetails.ScreenEventDetailsAdditionalState
import com.turitsynanton.android.wbtech.uinew.state.eventdetails.ScreenEventDetailsMainState
import com.turitsynanton.android.wbtech.uinew.utils.buttonstates.EventRegistrationButtonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "ScreenEventDetailsViewModel"

internal class ScreenEventDetailsViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val eventMapper: EventMapper,
    private val communityMapper: CommunityMapper,
    private val personCardMapper: PersonCardMapper,
    private val getEventDetails: IGetEventDetailsUseCase,
    private val getCommunityIdByEventId: IGetCommunityIdByEventIdUseCase,
    private val getOtherEvents: IGetOtherEventsUseCase,
    private val getParticipantsListUseCase: IGetParticipantsListUseCase,
    private val disableButtonForPastEvent: IDisableButtonForPastEventUseCase,
    private val isRegisteredForEvent: IIsRegisteredForEventUseCase,
    private val getVisitorsCount: IGetVisitorsCountUseCase
) : ViewModel() {

    private val _eventDetails: MutableStateFlow<UiEvent?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEvent?> = _eventDetails.asStateFlow()

    private val _otherEventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val otherEventList: StateFlow<List<UiEventCard>> = _otherEventList.asStateFlow()

    private val _communityDetails: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val communityDetails: StateFlow<UiCommunity?> = _communityDetails.asStateFlow()

    private val _participantsList: MutableStateFlow<List<UiPersonCard>> =
        MutableStateFlow(emptyList())
    private val participantsList: StateFlow<List<UiPersonCard>> = _participantsList

    private val _buttonStatus: MutableStateFlow<EventRegistrationButtonState> =
        MutableStateFlow(EventRegistrationButtonState())
    private val buttonStatus: StateFlow<EventRegistrationButtonState> = _buttonStatus

    private val _visitorsCount: MutableStateFlow<Int> = MutableStateFlow(0)
    private val visitorsCount: StateFlow<Int> = _visitorsCount.asStateFlow()

    init {
        getEventDetails(eventId)
        getCommunityDetailsByEventId(eventId)
        getOtherEvents(eventId)
        getParticipantsList(eventId)
        getButtonStatus(eventId)
        getRegisteredStatus(eventId)
        getVisitorsCount(eventId)
    }

    private fun getEventDetailsFlow(): StateFlow<UiEvent?> = eventDetails
    private fun getOtherEventsFlow(): StateFlow<List<UiEventCard>> = otherEventList
    private fun getCommunityDetailsFlow(): StateFlow<UiCommunity?> = communityDetails
    private fun getParticipantsListFlow(): StateFlow<List<UiPersonCard>> = participantsList
    private fun getButtonStatusFlow(): StateFlow<EventRegistrationButtonState> = buttonStatus
    private fun getVisitorsCountFlow(): StateFlow<Int> = visitorsCount

    val mainScreenState: StateFlow<ScreenEventDetailsMainState> = combine(
        getEventDetailsFlow(),
        getOtherEventsFlow(),
        getCommunityDetailsFlow(),
        getParticipantsListFlow()
    ) { eventDetails, otherEventList, communityDetails, participantsList ->
        ScreenEventDetailsMainState(
            eventDetails = eventDetails,
            otherEvents = otherEventList,
            communityDetails = communityDetails,
            participants = participantsList,
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventDetailsMainState())

    val additionalScreenState: StateFlow<ScreenEventDetailsAdditionalState> = combine(
        getButtonStatusFlow(),
        getVisitorsCountFlow()
    ) { buttonStatus, visitorsCount ->
        ScreenEventDetailsAdditionalState(
            buttonStatus = buttonStatus,
            visitorsCount = visitorsCount
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventDetailsAdditionalState())

    private fun getEventDetails(eventsId: String) {
        viewModelScope.launch {
            getEventDetails.execute(eventsId).collect { eventDetails ->
                _eventDetails.update { eventMapper.mapToUi(eventDetails) }
            }
        }
    }

    private fun getCommunityDetailsByEventId(eventId: String) {
        viewModelScope.launch {
            getCommunityIdByEventId.execute(eventId).collect { community ->
                _communityDetails.update {
                    community?.let { communityToMap ->
                        communityMapper.mapToUi(
                            communityToMap
                        )
                    }
                }
            }
        }
    }

    private fun getOtherEvents(eventId: String) {
        viewModelScope.launch {
            getOtherEvents.execute(eventId = eventId).collect { list ->
                _otherEventList.update { list.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    private fun getParticipantsList(eventId: String) {
        viewModelScope.launch {
            getParticipantsListUseCase.execute(eventId).collect { list ->
                _participantsList.update {
                    list.map { personCardMapper.mapToUi(it) }
                }
            }
        }
    }

    private fun getButtonStatus(eventId: String) {
        viewModelScope.launch {
            disableButtonForPastEvent.execute(eventId).collect { buttonStatus ->
                updateButtonStatus { buttonState ->
                    buttonState.copy(buttonStatus = buttonStatus)
                }
            }
        }
    }

    private fun getRegisteredStatus(eventId: String) {
        viewModelScope.launch {
            isRegisteredForEvent.execute(eventId).collect { isRegistered ->
                updateButtonStatus { buttonState ->
                    buttonState.copy(isRegistered = isRegistered)
                }
            }
        }
    }

    private fun updateButtonStatus(onUpdate: (EventRegistrationButtonState) -> EventRegistrationButtonState) {
        _buttonStatus.update { onUpdate(it) }
    }

    private fun getVisitorsCount(eventId: String) {
        viewModelScope.launch {
            getVisitorsCount.execute(eventId).collect { count ->
                _visitorsCount.update { count }
            }
        }
    }
}