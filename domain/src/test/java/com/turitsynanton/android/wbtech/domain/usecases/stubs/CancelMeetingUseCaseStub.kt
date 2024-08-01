package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.meeting.ICancelMeetingUseCase

class CancelMeetingUseCaseStub : ICancelMeetingUseCase {
    var returnValue: Boolean = true

    override fun execute(): Boolean {
        return returnValue
    }
}