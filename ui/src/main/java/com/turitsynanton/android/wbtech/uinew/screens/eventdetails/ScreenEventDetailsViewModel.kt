package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetVisitorsCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IDisableButtonForPastEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IIsRegisteredForEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import com.turitsynanton.android.wbtech.uinew.state.ScreenEventDetailsState
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
    private val iGetEventDetails: IGetEventDetailsUseCase,
    private val iGetCommunityIdByEventId: IGetCommunityIdByEventIdUseCase,
    private val getCommunityIdByEventId: IGetCommunityIdByEventIdUseCaseNew,
    private val getOtherEvents: IGetOtherEventsUseCase,
    private val getParticipantsListUseCase: IGetParticipantsListUseCase,
    private val disableButtonForPastEventUseCase: IDisableButtonForPastEventUseCase,
    private val isRegisteredForEventUseCase: IIsRegisteredForEventUseCase,
    private val getVisitorsCount: IGetVisitorsCountUseCase
) : ViewModel() {

    private val _eventDetails: MutableStateFlow<UiEvent?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEvent?> = _eventDetails.asStateFlow()

    private val _otherEventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val otherEventList: StateFlow<List<UiEventCard>> = _otherEventList.asStateFlow()

    private val _communityDetails: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val communityDetails: StateFlow<UiCommunity?> = _communityDetails.asStateFlow()

    private val _communityId: MutableStateFlow<String> = MutableStateFlow("")
    private val communityId: StateFlow<String> = _communityId.asStateFlow()

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
        getCommunityIdByEventId.execute(eventId)
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
    fun getVisitorsCountFlow(): StateFlow<Int> = visitorsCount

    val screenState: StateFlow<ScreenEventDetailsState> = combine(
        getEventDetailsFlow(),
        getOtherEventsFlow(),
        getCommunityDetailsFlow(),
        getParticipantsListFlow(),
        getButtonStatusFlow()
    ) { eventDetails, otherEventList, communityDetails, participantsList, buttonStatus ->
        ScreenEventDetailsState(
            eventDetails = eventDetails,
            otherEvents = otherEventList,
            communityDetails = communityDetails,
            participants = participantsList,
            buttonStatus = buttonStatus
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventDetailsState())

    private fun getEventDetails(eventsId: String) {
        viewModelScope.launch {
            iGetEventDetails.execute(eventsId).collect { eventDetails ->
                _eventDetails.update { eventMapper.mapToUi(eventDetails) }
            }
        }
    }

    private fun getCommunityDetailsByEventId(eventId: String) {
        getCommunityIdByEventId.execute(eventId)
        viewModelScope.launch {
            /*interactorFullInfo.invoke().collect { community ->
                Log.d("TAG", "eventList: ${community.eventList}")
                Log.d("TAG", "communitiesList: ${community.communitiesList}")
                Log.d("TAG", "filteredEvents: ${community.filteredEvents}")
                Log.d("TAG", "communityId: ${community.communityId}")
                _communityId.update { community.communityId }
            }*/
            iGetCommunityIdByEventId.execute(eventId).collect { community ->
                Log.d("TAG", "communityId.value: ${communityId}")
                _communityDetails.update { community?.let { it1 -> communityMapper.mapToUi(it1) } }
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
            disableButtonForPastEventUseCase.execute(eventId).collect { buttonStatus ->
                updateButtonStatus { buttonState ->
                    buttonState.copy(buttonStatus = buttonStatus)
                }
            }
        }
    }

    private fun getRegisteredStatus(eventId: String) {
        viewModelScope.launch {
            isRegisteredForEventUseCase.execute(eventId).collect { isRegistered ->
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