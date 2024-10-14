package com.turitsynanton.android.wbtech.uinew.screens.eventslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.community.IIsSubscribedToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.ISubscribeToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IUpdateSearchQueryUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.filter.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.uinew.state.EventsListState
import com.turitsynanton.android.wbtech.uinew.utils.buttonstates.CommunitySubscribeButtonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "ScreenEventsListViewModel"

internal class ScreenEventsListViewModel(
    private val eventCardMapper: EventCardMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val interactorFullInfo: IInfoEventListScreenInteractor,
    private val iGetIFilterEventsUseCase: IFilterEventsUseCase,
    private val getUpcomingEventsUseCase: IGetUpcomingEventsUseCase,
    private val updateSearchQueryUseCase: IUpdateSearchQueryUseCase,
    private val subscribeToCommunity: ISubscribeToCommunityUseCase,
    private val isSubscribedToCommunityUseCase: IIsSubscribedToCommunityUseCase
) : ViewModel() {

    private val _eventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventList: StateFlow<List<UiEventCard>> = _eventList.asStateFlow()

    private val _communitiesList: MutableStateFlow<List<UiCommunityCard>> =
        MutableStateFlow(emptyList())
    private val communitiesList: StateFlow<List<UiCommunityCard>> = _communitiesList.asStateFlow()

    private val _filteredEventList: MutableStateFlow<List<UiEventCard>> =
        MutableStateFlow(emptyList())
    private val filteredEventList: StateFlow<List<UiEventCard>> = _filteredEventList.asStateFlow()

    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedTags: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    private val _upcomingEventList: MutableStateFlow<List<UiEventCard>> =
        MutableStateFlow(emptyList())
    private val upcomingEventList: StateFlow<List<UiEventCard>> = _upcomingEventList.asStateFlow()

    private val _buttonStatus: MutableStateFlow<CommunitySubscribeButtonState> =
        MutableStateFlow(CommunitySubscribeButtonState())
    private val buttonStatus: StateFlow<CommunitySubscribeButtonState> = _buttonStatus

    private fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventList
    private fun getCommunitiesListFlow(): StateFlow<List<UiCommunityCard>> = communitiesList
    private fun getFilteredListFlow(): StateFlow<List<UiEventCard>> = filteredEventList
    private fun getSearchQueryFlow(): StateFlow<String> = searchQuery
    private fun getUpcomingEventListFlow(): StateFlow<List<UiEventCard>> = upcomingEventList
    fun getButtonStatusFlow(): StateFlow<CommunitySubscribeButtonState> = buttonStatus

    val screenState: StateFlow<EventsListState> = combine(
        getEventsListFlow(),
        getCommunitiesListFlow(),
        getFilteredListFlow(),
        getSearchQueryFlow(),
        getUpcomingEventListFlow()
    ) { eventList, communitiesList, filteredEventList, searchQuery, upcomingEvents ->
        EventsListState.Loaded(
            events = eventList,
            communities = communitiesList,
            filteredEvents = filteredEventList,
            searchQuery = searchQuery,
            selectedTags = emptyList(),
            upcomingEvents = upcomingEvents
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        EventsListState.Loading
    )

    init {
        getEventsList()
        getCommunitiesList()
        updateFilteredEventsList("")
        upcomingEvents()
    }

    private fun getEventsList() {
        viewModelScope.launch {
            interactorFullInfo.invoke()
                .collect { list ->
                    _eventList.update { list.eventList.map { eventCardMapper.mapToUi(it) } }
                    updateFilteredEventsList(_searchQuery.value)
                }
        }
    }

    private fun getCommunitiesList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "getList: ${list.communitiesList}")
                _communitiesList.update {
                    list.communitiesList.map {
                        communityCardMapper.mapToUi(it)
                    }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        viewModelScope.launch {
            updateSearchQueryUseCase.execute(query).collect { query ->
                _searchQuery.update {
                    Log.d("TAG", "updateSearchQuery: $query")
                    query
                }
                updateFilteredEventsList(query)
            }
//        filterEventUseCaseNew.execute(query)
        }
    }

    private fun updateFilteredEventsList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            iGetIFilterEventsUseCase.execute(query)
                .collect { filteredList ->
                    _filteredEventList.update { filteredList.map { eventCardMapper.mapToUi(it) } }
                }
            /*interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "updateFilteredEventsList: ${list.filteredEvents}")
                _filteredEventList.update { list.filteredEvents.map { eventCardMapper.mapToUi(it) } }
            }*/
        }
        if (query.isEmpty()) {
            _selectedTags.update { emptyList() }
        }
    }

    fun onTagSelected(tag: String) {
        val updatedTags = if (_selectedTags.value.contains(tag)) {
            _selectedTags.value - tag
        } else {
            _selectedTags.value + tag
        }
        _selectedTags.value = updatedTags
        filterEventsByTags(updatedTags)
    }

    private fun filterEventsByTags(tags: List<String>) {
        if (tags.isEmpty()) {
            _filteredEventList.update { _eventList.value }
        } else {
            _filteredEventList.update {
                _eventList.value.filter { event ->
                    event.tags.any { it.content in tags }
                }
            }
        }
    }

    fun isTagSelected(tag: String): Boolean {
        return _selectedTags.value.contains(tag)
    }

    private fun upcomingEvents() {
        viewModelScope.launch {
            getUpcomingEventsUseCase.execute().collect { list ->
                Log.d(TAG, "upcomingEvents: $list")
                _upcomingEventList.update { list.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }

    fun subscribeToCommunity(communityId: String) {
        viewModelScope.launch {
            subscribeToCommunity.execute(communityId)
        }
    }

    //TODO переделать это говно
    /*fun getSubscribedStatus(communityId: String): Boolean {
        Log.d(TAG, "getSubscribedStatus: EXECUTE")
        val isSubscribedVal: MutableStateFlow<Boolean> = MutableStateFlow(false)
        viewModelScope.launch {
            isSubscribedToCommunityUseCase.execute(communityId).collect { isSubscribed ->
                Log.d(TAG, "isRegistered: $isSubscribed")
                updateButtonStatus { buttonState ->
                    buttonState.copy(isSubscribed = isSubscribed)

                }
                isSubscribedVal.update {
                    isSubscribed
                }
            }
        }
        return isSubscribedVal.value
    }*/

    private fun updateButtonStatus(onUpdate: (CommunitySubscribeButtonState) -> CommunitySubscribeButtonState) {
        _buttonStatus.update { onUpdate(it) }
    }
}