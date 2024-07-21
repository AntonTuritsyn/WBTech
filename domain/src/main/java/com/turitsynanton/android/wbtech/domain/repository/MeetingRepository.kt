package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.Meeting
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {
    fun getMeetingsList() : Flow<List<Meeting>>

    fun getMeetingDetails(meetingId: Long) : Meeting?
}