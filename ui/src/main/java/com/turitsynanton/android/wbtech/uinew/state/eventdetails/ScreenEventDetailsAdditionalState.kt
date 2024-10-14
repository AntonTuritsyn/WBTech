package com.turitsynanton.android.wbtech.uinew.state.eventdetails

import com.turitsynanton.android.wbtech.uinew.utils.buttonstates.EventRegistrationButtonState

internal data class ScreenEventDetailsAdditionalState(
    val buttonStatus: EventRegistrationButtonState = EventRegistrationButtonState(),
    val visitorsCount: Int = 0
)