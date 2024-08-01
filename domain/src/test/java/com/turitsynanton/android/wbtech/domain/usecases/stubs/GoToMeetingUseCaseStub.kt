package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGoToMeetingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GoToMeetingUseCaseStub : IGoToMeetingUseCase {
    var returnValue: Boolean = true

    override fun execute(): Flow<Boolean> {
        return flowOf(returnValue)
    }
}