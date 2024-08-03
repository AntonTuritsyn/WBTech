package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import kotlinx.coroutines.flow.Flow

internal class GetMeetingDetailsUseCase(private val meetingRepository: MeetingRepository) :
    IGetMeetingDetailsUseCase {
    override fun execute(meetingId: String): Flow<DomainMeeting> =
        meetingRepository.getMeetingDetails(meetingId)
}