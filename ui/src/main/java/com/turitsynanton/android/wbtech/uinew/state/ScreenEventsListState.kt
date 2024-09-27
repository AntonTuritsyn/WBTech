package com.turitsynanton.android.wbtech.uinew.state

import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import kotlinx.coroutines.flow.StateFlow

internal data class ScreenEventsListState(
    val events: List<UiEventCard> = emptyList(),
    val communities: List<UiCommunityCard> = emptyList(),
    val filteredEvents: List<UiEventCard> = emptyList(),
    val searchQuery: String = "",
    val selectedTags: List<String> = emptyList(),
    val upcomingEvents: List<UiEventCard> = emptyList()
)

sealed class EventsListState {
    data object Loading : EventsListState()
    data class Loaded(
        val events: List<UiEventCard> = emptyList(),
        val communities: List<UiCommunityCard> = emptyList(),
        val filteredEvents: List<UiEventCard> = emptyList(),
        val searchQuery: String = "",
        val selectedTags: List<String> = emptyList(),
        val upcomingEvents: List<UiEventCard> = emptyList()
    ) : EventsListState()
    data class Error(val message: String) : EventsListState()
}
