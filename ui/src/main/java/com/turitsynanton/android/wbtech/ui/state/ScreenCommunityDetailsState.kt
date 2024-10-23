package com.turitsynanton.android.wbtech.ui.state

import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.ui.utils.buttonstates.CommunitySubscribeButtonState

internal data class ScreenCommunityDetailsState(
    val communityDetails: UiCommunity? = null,
    val eventsList: List<UiEventCard> = emptyList(),
    val pastEventsList: List<UiEventCard> = emptyList(),
    val buttonState: CommunitySubscribeButtonState = CommunitySubscribeButtonState(),
    val subscribersCount: Int = 0
)
