package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository

class GetMeetingDetailsUseCase(private val meetingRepository: MeetingRepository) {
    fun execute(meetingId: Long): DomainMeeting? =
        meetingRepository.getMeetingDetails(meetingId)
}