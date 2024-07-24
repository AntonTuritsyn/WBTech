package com.turitsynanton.android.wbtech.domain.repository.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {
    fun getMeetingsList() : Flow<List<DomainMeeting>>

    fun getMeetingDetails(meetingId: Long) : DomainMeeting?
}