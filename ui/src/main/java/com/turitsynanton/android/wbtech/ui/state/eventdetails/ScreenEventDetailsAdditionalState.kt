package com.turitsynanton.android.wbtech.ui.state.eventdetails

import com.turitsynanton.android.wbtech.ui.utils.buttonstates.EventRegistrationButtonState

internal data class ScreenEventDetailsAdditionalState(
    val buttonStatus: EventRegistrationButtonState = EventRegistrationButtonState(),
    val visitorsCount: Int = 0
)