package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingsListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetMeetingsListUseCaseStub : IGetMeetingsListUseCase {
    private var meetingsList: List<DomainMeeting> = emptyList()

    fun setMeetingsList(meetings: List<DomainMeeting>) {
        meetingsList = meetings
    }

    override fun execute(): Flow<List<DomainMeeting>> {
        return flowOf(meetingsList)
    }
}