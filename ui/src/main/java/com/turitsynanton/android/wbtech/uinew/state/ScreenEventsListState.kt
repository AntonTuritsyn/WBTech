package com.turitsynanton.android.wbtech.uinew.state

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard

data class ScreenEventsListState(
    val events: List<UiEventCard> = emptyList(),
    val communities: List<UiCommunityCard> = emptyList(),
    val filteredEvents: List<UiEventCard> = emptyList(),
    val searchQuery: String = "",
    val selectedTags: List<String> = emptyList(),
    val upcomingEvents: List<UiEventCard> = emptyList()
)
