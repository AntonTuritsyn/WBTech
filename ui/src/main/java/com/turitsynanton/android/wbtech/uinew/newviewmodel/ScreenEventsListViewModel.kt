package com.turitsynanton.android.wbtech.uinew.newviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainTag
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenEventsListViewModel(
    private val iGetEventListUseCase: IGetEventListUseCase,
    private val iGetCommunitiesListUseCase: IGetCommunitiesListUseCase
) : ViewModel() {
    private val _eventList: MutableStateFlow<List<DomainEvent>> = MutableStateFlow(emptyList())
    private val eventList: StateFlow<List<DomainEvent>> = _eventList.asStateFlow()

    private val _communitiesList: MutableStateFlow<List<DomainCommunity>> =
        MutableStateFlow(emptyList())
    private val communitiesList: StateFlow<List<DomainCommunity>> = _communitiesList.asStateFlow()

    private val _filteredEventList: MutableStateFlow<List<DomainEvent>> =
        MutableStateFlow(emptyList())
    private val filteredEventList: StateFlow<List<DomainEvent>> = _filteredEventList.asStateFlow()

    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedTags: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
//    val selectedTags: StateFlow<List<String>> = _selectedTags

    fun getEventsListFlow(): StateFlow<List<DomainEvent>> = eventList
    fun getCommunitiesListFlow(): StateFlow<List<DomainCommunity>> = communitiesList
    fun getFilteredListFlow(): StateFlow<List<DomainEvent>> = filteredEventList
    fun getSearchQueryFlow(): StateFlow<String> = searchQuery


    init {
        getEventsList()
        getCommunitiesList()
        updateFilteredEventsList("")
    }

    private fun getEventsList() {
        viewModelScope.launch {
            iGetEventListUseCase.execute().collect { eventList ->
                _eventList.update { eventList }
                updateFilteredEventsList(_searchQuery.value)
            }
        }
    }

    private fun getCommunitiesList() {
        viewModelScope.launch {
            iGetCommunitiesListUseCase.execute().collect { communitiesList ->
                _communitiesList.update { communitiesList }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.update {
            query
        }
        updateFilteredEventsList(query)
    }

    private fun updateFilteredEventsList(query: String) {
        if (query.isEmpty()) {
            _filteredEventList.update { _eventList.value }
            _selectedTags.update { emptyList() }
        } else {
            val filteredList = _eventList.value.filter { event ->
                event.city.contains(query, ignoreCase = true) ||
                        event.name.contains(query, ignoreCase = true)
            }
            _filteredEventList.update { filteredList }
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
}
