package com.turitsynanton.android.wbtech.uinew.state

import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard

internal data class ScreenCommunityDetailsState(
    val communityDetails: UiCommunity? = null,
    val eventsList: List<UiEventCard> = emptyList(),
    val pastEventsList: List<UiEventCard> = emptyList()
)
