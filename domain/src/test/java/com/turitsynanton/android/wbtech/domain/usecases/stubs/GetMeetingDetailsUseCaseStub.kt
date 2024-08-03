package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetMeetingDetailsUseCaseStub : IGetMeetingDetailsUseCase {
    private var meetingDetails: Map<String, DomainMeeting> = emptyMap()

    fun setMeetingDetails(details: Map<String, DomainMeeting>) {
        meetingDetails = details
    }

    override fun execute(meetingId: String): Flow<DomainMeeting> {
        return flowOf(meetingDetails[meetingId] ?: throw MeetingNotFoundException(meetingId))
    }

    class MeetingNotFoundException(meetingId: String) : Exception("Meeting $meetingId not found")
}