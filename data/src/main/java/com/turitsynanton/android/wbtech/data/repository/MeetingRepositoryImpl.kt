package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MeetingRepositoryImpl : MeetingRepository {
    override fun getMeetingsList(): Flow<List<DomainMeeting>> =
        flow { emit(dataMeetings) }.map { it.mapMeetingToDomain() }

    override fun getMeetingDetails(meetingId: Long): Flow<DomainMeeting> =
        flow { dataMeetings.find { it.id == meetingId }?.mapMeetingToDomain()?.let { emit(it) } }

    override fun goToMeeting(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancelMeeting(): Boolean {
        TODO("Not yet implemented")
    }

}

//      временно закинул сюда
val meetingTags = listOf(
    MeetingTag(
        "Java"
    ),
    MeetingTag(
        "Kotlin"
    ),
    MeetingTag(
        "Android"
    )
)