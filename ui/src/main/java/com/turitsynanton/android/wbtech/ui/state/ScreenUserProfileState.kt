package com.turitsynanton.android.wbtech.ui.state

import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson

internal data class ScreenUserProfileState(
    val user: UiPerson? = null,
    val events: List<UiEventCard> = emptyList(),
    val communities: List<UiCommunityCard> = emptyList(),
)
