package com.turitsynanton.android.wbtech.ui.state.registration

import com.turitsynanton.android.wbtech.models.UiEventCard

internal data class MainRegistrationState(
    val event: UiEventCard? = null,
    val step: Int = 0
)