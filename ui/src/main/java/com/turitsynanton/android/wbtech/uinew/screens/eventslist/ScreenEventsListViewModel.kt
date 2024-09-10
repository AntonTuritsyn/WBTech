package com.turitsynanton.android.wbtech.uinew.screens.eventslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.CombinedEventInfo
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.IFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.IInnerFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.uinew.state.ScreenEventsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class ScreenEventsListViewModel(
    private val eventCardMapper: EventCardMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val interactorFullInfo: IInfoEventListScreenInteractor,
    private val iGetIFilterEventsUseCase: IFilterEventsUseCase,
    private val filterEventUseCaseNew: IFilterEventUseCaseNew,
    private val getUpcomingEventsUseCase: IGetUpcomingEventsUseCase
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

    fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventList
    fun getCommunitiesListFlow(): StateFlow<List<UiCommunityCard>> = communitiesList
    fun getFilteredListFlow(): StateFlow<List<UiEventCard>> = filteredEventList
    fun getSearchQueryFlow(): StateFlow<String> = searchQuery
    fun getUpcomingEventListFlow(): StateFlow<List<UiEventCard>> = upcomingEventList

    val screenState: StateFlow<ScreenEventsListState> = combine(
        getEventsListFlow(),
        getCommunitiesListFlow(),
        getFilteredListFlow(),
        getSearchQueryFlow(),
        getUpcomingEventListFlow()
    ) { eventList, communitiesList, filteredEventList, searchQuery, upcomingEvents ->
        ScreenEventsListState(
            events = eventList,
            communities = communitiesList,
            filteredEvents = filteredEventList,
            searchQuery = searchQuery,
            selectedTags = emptyList(),
            upcomingEvents = upcomingEvents
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventsListState())

    init {
        getEventsList()
        getCommunitiesList()
        updateFilteredEventsList("")
        upComingEvents()
    }

    private fun getEventsList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                _eventList.update { list.eventList.map { eventCardMapper.mapToUi(it) } }
                updateFilteredEventsList(_searchQuery.value)
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

    fun updateSearchQuery(query: String) {
        viewModelScope.launch {
            _searchQuery.update {
                query
            }
        }
        filterEventUseCaseNew.execute(query)
        updateFilteredEventsList(query)
    }

    private fun updateFilteredEventsList(query: String) {
        viewModelScope.launch {
            /*interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "updateFilteredEventsList: ${list.filteredEvents}")
                _filteredEventList.update { list.filteredEvents.map { eventCardMapper.mapToUi(it) } }
            }*/
            iGetIFilterEventsUseCase.execute(query)
                .collect { filteredList ->
                    _filteredEventList.update { filteredList.map { eventCardMapper.mapToUi(it) } }
                }
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

    private fun upComingEvents() {
        viewModelScope.launch {
            getUpcomingEventsUseCase.execute().collect { list ->
                Log.d("TAG", "upComingEvents: $list")
                _upcomingEventList.update { list.map { eventCardMapper.mapToUi(it) } }
            }
        }
    }
}