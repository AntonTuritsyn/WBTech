package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.data.storage.models.DataMeetingTag
import com.turitsynanton.android.wbtech.domain.models.Meeting
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MeetingRepositoryImpl : MeetingRepository {
    override fun getMeetingsList(): Flow<List<Meeting>> =
        flow { emit(dataMeetings) }.map { it.mapToDomain() }

    override fun getMeetingDetails(meetingId: Long): Meeting? =
        dataMeetings.find { it.id == meetingId }?.mapToDomain()

    private fun DataMeeting.mapToDomain() : Meeting {
        return Meeting(
            id = id,
            name = name,
            date = date,
            city = city,
            ended = ended,
            tags = meetingTags
        )
    }

    private fun List<DataMeeting>.mapToDomain(): List<Meeting> {
        return map { it.mapToDomain() }
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