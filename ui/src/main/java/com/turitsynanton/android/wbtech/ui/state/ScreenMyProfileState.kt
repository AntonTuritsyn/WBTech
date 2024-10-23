package com.turitsynanton.android.wbtech.ui.state

import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson

internal data class ScreenMyProfileState(
    val user: UiPerson? = null,
    val eventsList: List<UiEventCard> = emptyList(),
    val communitiesList: List<UiCommunityCard> = emptyList(),
    val editMode: Boolean = false,
    val isEventsVisible: Boolean = true,
    val isCommunitiesVisible: Boolean = true
)
