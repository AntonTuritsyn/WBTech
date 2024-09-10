package com.turitsynanton.android.wbtech.uinew.state

import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPersonCard

data class ScreenEventDetailsState(
    val eventDetails: UiEvent? = null,
    val otherEvents: List<UiEventCard> = emptyList(),
    val communityDetails: UiCommunity? = null,
    val participants: List<UiPersonCard> = emptyList(),
    val buttonStatus: Boolean = false
)
