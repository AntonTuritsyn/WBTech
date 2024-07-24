package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import kotlinx.coroutines.flow.Flow

class GetMeetingListUseCase(private val meetingRepository: MeetingRepository) {
    fun execute(): Flow<List<DomainMeeting>> = meetingRepository.getMeetingsList()
}