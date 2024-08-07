package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.repository.mapper.communities.CommunityMapper
import com.turitsynanton.android.wbtech.data.repository.mapper.meetings.MeetingMapper
import com.turitsynanton.android.wbtech.data.repository.mapper.meetings.mapMeetingToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class MeetingRepositoryImpl(private val mapper: MeetingMapper) : MeetingRepository {
    override fun getMeetingsList(): Flow<List<DomainMeeting>> =
        flow { emit(dataMeetings) }.map { it.mapMeetingToDomain(mapper) }

    override fun getMeetingDetails(meetingId: String): Flow<DomainMeeting> =
        flow { dataMeetings.find { it.id == meetingId }?.mapMeetingToDomain(mapper)?.let { emit(it) } }

    override fun goToMeeting(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun cancelMeeting(): Flow<Boolean> {
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