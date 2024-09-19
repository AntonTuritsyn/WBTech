package com.turitsynanton.android.wbtech.uinew.screens.communitydetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.EventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetPastEventsUseCase
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.uinew.state.ScreenCommunityDetailsState
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
    private val iGetCommunityDetailsUseCase: IGetCommunityDetailsUseCase,
    private val eventsListByCommunityIdUseCase: EventsListByCommunityIdUseCase,
    private val getPastEventsUseCase: IGetPastEventsUseCase
) : ViewModel() {

    private val _community: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val community: StateFlow<UiCommunity?> = _community.asStateFlow()

    private val _eventsList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventsList: StateFlow<List<UiEventCard>> = _eventsList.asStateFlow()

    private val _pastEventList: MutableStateFlow<List<UiEventCard>> =
        MutableStateFlow(emptyList())
    private val pastEventList: StateFlow<List<UiEventCard>> = _pastEventList.asStateFlow()

    fun getCommunityDetailsFlow(): StateFlow<UiCommunity?> = community
    fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventsList
    fun getPastEventListFlow(): StateFlow<List<UiEventCard>> = pastEventList

    val screenState: StateFlow<ScreenCommunityDetailsState> = combine(
        getCommunityDetailsFlow(),
        getEventsListFlow(),
        getPastEventListFlow()
    ) { communityDetauls, eventsList, pastEventList ->
        ScreenCommunityDetailsState(
            communityDetails = communityDetauls,
            eventsList = eventsList,
            pastEventsList = pastEventList
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenCommunityDetailsState())

    init {
        getCommunityDetails(communityId)
        getEventsListByCommunityId(communityId)
        pastEvents()
        Log.d("TAG", "communityIdVM: $communityId")
    }

    fun getCommunityDetails(communityId: String) {
        viewModelScope.launch {
            iGetCommunityDetailsUseCase.execute(communityId).collect { communityDetails ->
                Log.d("TAG", "communityDetailsVM: $communityDetails")
                _community.update { communityMapper.mapToUi(communityDetails) }
            }
        }
    }

    fun getEventsListByCommunityId(communityId: String) {
        viewModelScope.launch {
            eventsListByCommunityIdUseCase.execute(communityId).collect { eventsList ->
                Log.d("TAG", "eventsListVMDetails: $eventsList")
                _eventsList.update { eventsList.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    private fun pastEvents() {
        viewModelScope.launch {
            getPastEventsUseCase.execute().collect { list ->
                Log.d("TAG", "pastEvents: $list")
                _pastEventList.update { list.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }
}