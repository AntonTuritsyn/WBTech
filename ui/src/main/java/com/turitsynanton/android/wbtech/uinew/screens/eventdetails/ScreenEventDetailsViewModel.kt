package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetails
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetailsUseCase2
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.IGetEventDetailsUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import com.turitsynanton.android.wbtech.uinew.state.ScreenEventDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenEventDetailsViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val eventMapper: EventMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val communityMapper: CommunityMapper,
    private val personCardMapper: PersonCardMapper,
    private val iGetEventDetailsUseCase: IGetEventDetailsUseCase,
    private val getEventDetailsUseCaseNew: IGetEventDetailsUseCaseNew,
    private val getEventIdUseCase: GetEventIdUseCase,
    private val getEventDetails: GetEventDetails,
    private val getEventDetailsUseCase2: GetEventDetailsUseCase2,
    private val interactorFullInfo: IInfoEventListScreenInteractor,
    private val iGetCommunityIdByEventIdUseCase: IGetCommunityIdByEventIdUseCase,
    private val getCommunityIdByEventIdUseCase: IGetCommunityIdByEventIdUseCaseNew,
    private val getOtherEventsUseCase: IGetOtherEventsUseCase,
    private val getParticipantsListUseCase: IGetParticipantsListUseCase
) : ViewModel() {

    private val _eventDetails: MutableStateFlow<UiEvent?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEvent?> = _eventDetails.asStateFlow()

    private val _eventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventList: StateFlow<List<UiEventCard>> = _eventList.asStateFlow()

    private val _otherEventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val otherEventList: StateFlow<List<UiEventCard>> = _otherEventList.asStateFlow()

    private val _communitiesList: MutableStateFlow<List<UiCommunityCard>> =
        MutableStateFlow(emptyList())
    private val communitiesList: StateFlow<List<UiCommunityCard>> = _communitiesList.asStateFlow()

    private val _communityDetails: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val communityDetails: StateFlow<UiCommunity?> = _communityDetails.asStateFlow()

    private val _communityId: MutableStateFlow<String> = MutableStateFlow("")
    private val communityId: StateFlow<String> = _communityId.asStateFlow()

    private val _participantsList: MutableStateFlow<List<UiPersonCard>> = MutableStateFlow(emptyList())
    private val participantsList: StateFlow<List<UiPersonCard>> = _participantsList


    init {
        getEventDetails(eventId)
        getList()
        getCommunityIdByEventIdUseCase.execute(eventId)
        getCommunityDetailsByEventId(eventId)
        getOtherEvents(eventId)
        getParticipantsList(eventId)
    }

    fun getEventDetailsFlow(): StateFlow<UiEvent?> = eventDetails
    fun getOtherEventDetailsFlow(): StateFlow<List<UiEventCard>> = otherEventList
    fun getCommunityDetailsFlow(): StateFlow<UiCommunity?> = communityDetails
    fun getParticipantsListFlow(): StateFlow<List<UiPersonCard>> = participantsList

    val screenState: StateFlow<ScreenEventDetailsState> = combine(
        getEventDetailsFlow(),
        getOtherEventDetailsFlow(),
        getCommunityDetailsFlow(),
        getParticipantsListFlow()
    ) { eventDetails, otherEventList, communityDetails, participantsList ->
        ScreenEventDetailsState(
            eventDetails = eventDetails,
            otherEvents = otherEventList,
            communityDetails = communityDetails,
            participants = participantsList
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventDetailsState())

    fun getEventDetails(eventsId: String) {
        viewModelScope.launch {
//            getEventDetails.invoke()
            iGetEventDetailsUseCase.execute(eventsId).collect { eventDetails ->
                _eventDetails.update { eventMapper.mapToUi(eventDetails) }
            }
        }
    }

    fun getList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "getList: $list")
                _eventList.update { list.eventList.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    private fun getCommunitiesList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "getList: ${list.communitiesList}")
                _communitiesList.update { list.communitiesList.map { communityCardMapper.mapToUi(it) } }
            }
        }
        /*viewModelScope.launch {
            iGetCommunitiesListUseCase.execute().collect { communitiesList ->
                _communitiesListDomain.update { communitiesList }
                _communitiesList.update { communitiesList.map { communityCardMapper.mapToUi(it) } }
            }
        }*/
    }

    fun getCommunityDetailsByEventId(eventId: String) {
        viewModelScope.launch {
            /*interactorFullInfo.invoke().collect { community ->
                Log.d("TAG", "communityId.value: ${community.communityId}")
                _communityId.update { community.communityId }
            }*/
            iGetCommunityIdByEventIdUseCase.execute(eventId).collect { community ->
                Log.d("TAG", "communityId.value: ${communityId}")
                _communityDetails.update { community?.let { it1 -> communityMapper.mapToUi(it1) } }
            }
        }
    }

    fun getOtherEvents(eventId: String) {
        viewModelScope.launch {
            getOtherEventsUseCase.execute(eventId = eventId).collect { list ->
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
}