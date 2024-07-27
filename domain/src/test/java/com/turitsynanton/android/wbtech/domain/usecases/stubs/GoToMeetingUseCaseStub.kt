package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGoToMeetingUseCase

class GoToMeetingUseCaseStub : IGoToMeetingUseCase {
    var returnValue: Boolean = true

    override fun execute(): Boolean {
        return returnValue
    }
}