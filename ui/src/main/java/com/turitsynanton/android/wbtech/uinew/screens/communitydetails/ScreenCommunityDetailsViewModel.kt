package com.turitsynanton.android.wbtech.uinew.screens.communitydetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.EventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.mapCommunityToUi
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
    private val iGetCommunityDetailsUseCase: IGetCommunityDetailsUseCase,
    private val communityMapper: CommunityMapper,
    private val eventCardMapper: EventCardMapper,
    private val eventsListByCommunityIdUseCase: EventsListByCommunityIdUseCase
) : ViewModel() {

    private val _community: MutableStateFlow<UiCommunity?> = MutableStateFlow(null)
    private val community: StateFlow<UiCommunity?> = _community.asStateFlow()

    private val _eventsList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventsList: StateFlow<List<UiEventCard>> = _eventsList.asStateFlow()

    fun getCommunityDetailsFlow(): StateFlow<UiCommunity?> = community
    fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventsList

    val screenState: StateFlow<ScreenCommunityDetailsState> = combine(
        getCommunityDetailsFlow(),
        getEventsListFlow()
    ) { communityDetauls, eventsList ->
        ScreenCommunityDetailsState(communityDetails = communityDetauls, eventsList = eventsList)
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenCommunityDetailsState())

    init {
        getCommunityDetails(communityId)
        getEventsListByCommunityId(communityId)
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
}