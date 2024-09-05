package com.turitsynanton.android.wbtech.uinew.screens.eventslist

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
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
//@RequiresApi(Build.VERSION_CODES.O)
internal class ScreenEventsListViewModel(
    private val iGetEventListUseCase: IGetEventListUseCase,
    private val iGetCommunitiesListUseCase: IGetCommunitiesListUseCase,
    private val iGetIFilterEventsUseCase: IFilterEventsUseCase,
    private val iGetCommunityIdByEventIdUseCase: IGetCommunityIdByEventIdUseCase,
    private val eventCardMapper: EventCardMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val interactorFullInfo: IInfoEventListScreenInteractor,
    private val innerFilterEventsUseCase: IInnerFilterEventUseCaseNew,
    private val filterEventUseCaseNew: IFilterEventUseCaseNew,
    private val getCommunityIdByEventIdUseCase: IGetCommunityIdByEventIdUseCaseNew
) : ViewModel() {

    private val _eventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val eventList: StateFlow<List<UiEventCard>> = _eventList.asStateFlow()
    private val _eventListDomain: MutableStateFlow<List<DomainEvent>> =
        MutableStateFlow(emptyList()) // костыль

    private val _communitiesList: MutableStateFlow<List<UiCommunityCard>> =
        MutableStateFlow(emptyList())
    private val communitiesList: StateFlow<List<UiCommunityCard>> = _communitiesList.asStateFlow()
    private val _communitiesListDomain: MutableStateFlow<List<DomainCommunity>> =
        MutableStateFlow(emptyList()) // костыль

    private val _filteredEventList: MutableStateFlow<List<UiEventCard>> =
        MutableStateFlow(emptyList())
    private val filteredEventList: StateFlow<List<UiEventCard>> = _filteredEventList.asStateFlow()

    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedTags: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
//    val selectedTags: StateFlow<List<String>> = _selectedTags

    private val _communityId: MutableStateFlow<String> = MutableStateFlow("")
    private val communityId: StateFlow<String> = _communityId.asStateFlow()

    private val _upcomingEventList: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val upcomingEventList: StateFlow<List<UiEventCard>> = _upcomingEventList.asStateFlow()

    fun getEventsListFlow(): StateFlow<List<UiEventCard>> = eventList
    fun getCommunitiesListFlow(): StateFlow<List<UiCommunityCard>> = communitiesList
    fun getFilteredListFlow(): StateFlow<List<UiEventCard>> = filteredEventList
    fun getSearchQueryFlow(): StateFlow<String> = searchQuery
    fun getCommunityIdFlow(): StateFlow<String> = communityId
    fun getUpcomingEventListFlow(): StateFlow<List<UiEventCard>> = upcomingEventList

    val screenState: StateFlow<ScreenEventsListState> = combine(
        getEventsListFlow(),
        getCommunitiesListFlow(),
        getFilteredListFlow(),
        getSearchQueryFlow(),
        getCommunityIdFlow(),
    ) { eventList, communitiesList, filteredEventList, searchQuery, communityId ->
        ScreenEventsListState(
            events = eventList,
            communities = communitiesList,
            filteredEvents = filteredEventList,
            searchQuery = searchQuery,
            selectedTags = emptyList(),
            communityId = communityId
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenEventsListState())

    init {
//        getEventsList()
        getList()
        getCommunitiesList()
        updateFilteredEventsList("")
        upComingEvents()
    }

    /*private fun getEventsList() {
        viewModelScope.launch {
            iGetEventListUseCase.execute().collect { eventList ->
                _eventListDomain.update { eventList }
                _eventList.update {
                    eventList.map { eventCardMapper.mapToUi(it) }
                }
                updateFilteredEventsList(_searchQuery.value)
            }
        }
    }*/

    private fun getCommunitiesList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "getList: ${list.communitiesList}")
                _communitiesListDomain.update { list.communitiesList }
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
        _searchQuery.update {
            query
        }
        Log.d("TAG", "updateSearchQuery: $query")
        filterEventUseCaseNew.execute(query, _eventListDomain.value)

        updateFilteredEventsList(query)
    }

    private fun updateFilteredEventsList(query: String) {
        viewModelScope.launch {
            /*interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "updateFilteredEventsList: ${list.filteredEvents}")
                _filteredEventList.update { list.filteredEvents.map { eventCardMapper.mapToUi(it) } }
            }*/
            iGetIFilterEventsUseCase.execute(query, _eventListDomain.value)
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

    fun getCommunityDetailsByEventId(eventId: String) {
        val communities = _communitiesListDomain.value

        viewModelScope.launch {
            /*getCommunityIdByEventIdUseCase.execute(eventId, communities)
            interactorFullInfo.invoke().collect { community ->
                Log.d("TAG", "communityId.value: ${community.communityId}")
                _communityId.update { community.communityId }
            }*/
            iGetCommunityIdByEventIdUseCase.execute(eventId, communities)?.collect { communityId ->
                Log.d("TAG", "communityId.value: ${communityId}")
                _communityId.update { communityId }
            }
        }
    }

    private val _combinedEventInfo = MutableStateFlow<CombinedEventInfo?>(null)
    val combinedEventInfo: StateFlow<CombinedEventInfo?> = _combinedEventInfo

    //    ______________________
    fun getList() {
        viewModelScope.launch {
            interactorFullInfo.invoke().collect { list ->
                Log.d("TAG", "getList: $list")
                _eventListDomain.update { list.eventList }
                _eventList.update { list.eventList.map { eventCardMapper.mapToUi(it) } }
                updateFilteredEventsList(_searchQuery.value)
            }
        }
    }

    // TODO в useCase
    fun upComingEvents() {
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val twoWeeksFromNow = currentDate.plusWeeks(2)
            val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

            _upcomingEventList.update {
                _eventListDomain.value.filter { sdf ->
                    val eventDate = LocalDate.parse(sdf.date, dateFormatter)
                    eventDate in currentDate..twoWeeksFromNow
                }.map { eventCardMapper.mapToUi(it) }
            }
        }
    }
}