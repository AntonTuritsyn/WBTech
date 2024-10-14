package com.turitsynanton.android.wbtech.uinew.state.eventdetails

import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPersonCard

internal data class ScreenEventDetailsMainState(
    val eventDetails: UiEvent? = null,
    val otherEvents: List<UiEventCard> = emptyList(),
    val communityDetails: UiCommunity? = null,
    val participants: List<UiPersonCard> = emptyList()
)
