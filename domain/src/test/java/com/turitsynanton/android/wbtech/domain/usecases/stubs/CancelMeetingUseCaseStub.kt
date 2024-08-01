package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.meeting.ICancelMeetingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CancelMeetingUseCaseStub : ICancelMeetingUseCase {
    var returnValue: Boolean = true

    override fun execute(): Flow<Boolean> {
        return flowOf(returnValue)
    }
}