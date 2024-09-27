package com.turitsynanton.android.wbtech.uinew.screens.communitydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetSubscribersCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.details.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IIsSubscribedToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.ISubscribeToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IUnsubscribeFromCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.EventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetPastEventsUseCase
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.uinew.state.ScreenCommunityDetailsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenCommunityDetailsViewModel(
    communityId: String,
    private val communityMapper: CommunityMapper,
    private val eventCardMapper: EventCardMapper,
    private val iGetCommunityDetails: IGetCommunityDetailsUseCase,
    private val eventsListByCommunityId: EventsListByCommunityIdUseCase,
    private val getPastEventsUseCase: IGetPastEventsUseCase,
    private val subscribeToCommunity: ISubscribeToCommunityUseCase,
    private val unsubscribeFromCommunity: IUnsubscribeFromCommunityUseCase,
    private val isSubscribedToCommunity: IIsSubscribedToCommunityUseCase,
    private val getSubscribersCount: IGetSubscribersCountUseCase
) : ViewModel() {

    private val _community: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val community: StateFlow<UiCommunity?> = _community.asStateFlow()

    private val _eventsList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventsList: StateFlow<List<UiEventCard>> = _eventsList.asStateFlow()

    private val _pastEventList: MutableStateFlow<List<UiEventCard>> =
        MutableStateFlow(emptyList())
    private val pastEventList: StateFlow<List<UiEventCard>> = _pastEventList.asStateFlow()

    private val _buttonStatus: MutableStateFlow<SubscribedButtonState> =
        MutableStateFlow(SubscribedButtonState())
    private val buttonStatus: StateFlow<SubscribedButtonState> = _buttonStatus

    private val _subscribersCount: MutableStateFlow<Int> = MutableStateFlow(0)
    private val subscribersCount: StateFlow<Int> = _subscribersCount.asStateFlow()

    private fun getCommunityDetailsFlow(): StateFlow<UiCommunity?> = community
    private fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventsList
    private fun getPastEventListFlow(): StateFlow<List<UiEventCard>> = pastEventList
    private fun getButtonStatusFlow(): StateFlow<SubscribedButtonState> = buttonStatus
    private fun getSubscribersCountFlow(): StateFlow<Int> = subscribersCount

    val screenState: StateFlow<ScreenCommunityDetailsState> = combine(
        getCommunityDetailsFlow(),
        getEventsListFlow(),
        getPastEventListFlow(),
        getButtonStatusFlow(),
        getSubscribersCountFlow()
    ) { communityDetails, eventsList, pastEventList, buttonState, subscribersCount ->
        ScreenCommunityDetailsState(
            communityDetails = communityDetails,
            eventsList = eventsList,
            pastEventsList = pastEventList,
            buttonState = buttonState,
            subscribersCount = subscribersCount
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenCommunityDetailsState())

    init {
        getCommunityDetails(communityId)
        getEventsListByCommunityId(communityId)
        pastEvents()
        getSubscribedStatus(communityId)
        getSubscribersCount(communityId)
    }

    private fun getCommunityDetails(communityId: String) {
        viewModelScope.launch {
            iGetCommunityDetails.execute(communityId).collect { communityDetails ->
                _community.update { communityMapper.mapToUi(communityDetails) }
            }
        }
    }

    private fun getEventsListByCommunityId(communityId: String) {
        viewModelScope.launch {
            eventsListByCommunityId.execute(communityId).collect { eventsList ->
                _eventsList.update { eventsList.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    private fun pastEvents() {
        viewModelScope.launch {
            getPastEventsUseCase.execute().collect { list ->
                _pastEventList.update { list.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    fun subscribeToCommunity(communityId: String) {
        viewModelScope.launch {
            subscribeToCommunity.execute(communityId)
//            TODO здесь добавить обработку ответа от сервера после подписки
            delay(400)
            getSubscribedStatus(communityId)
            getSubscribersCount(communityId)
        }
    }

    fun unsubscribeFromCommunity(communityId: String) {
        viewModelScope.launch {
            unsubscribeFromCommunity.execute(communityId)
//            TODO здесь добавить обработку ответа от сервера после подписки
            delay(400)
            getSubscribedStatus(communityId)
            getSubscribersCount(communityId)
        }
    }

    private fun getSubscribedStatus(communityId: String) {
        viewModelScope.launch {
            isSubscribedToCommunity.execute(communityId).collect { isSubscribed ->
                updateButtonStatus { buttonState ->
                    buttonState.copy(isSubscribed = isSubscribed)
                }
            }
        }
    }

    private fun updateButtonStatus(onUpdate: (SubscribedButtonState) -> SubscribedButtonState) {
        _buttonStatus.update { onUpdate(it) }
    }

    private fun getSubscribersCount(communityId: String) {
        viewModelScope.launch {
            getSubscribersCount.execute(communityId).collect { count ->
                _subscribersCount.update { count }
            }
        }
    }
}

internal data class SubscribedButtonState(
    val buttonStatus: Boolean = false,
    val isSubscribed: Boolean = false
)