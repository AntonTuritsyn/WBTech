package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MeetingRepositoryImpl : MeetingRepository {
    override fun getMeetingsList(): Flow<List<DomainMeeting>> =
        flow { emit(dataMeetings) }.map { it.mapToDomain() }

    override fun getMeetingDetails(meetingId: Long): DomainMeeting? =
        dataMeetings.find { it.id == meetingId }?.mapToDomain()

    private fun DataMeeting.mapToDomain() : DomainMeeting {
        return DomainMeeting(
            id = id,
            name = name,
            date = date,
            city = city,
            ended = ended,
            tags = meetingTags
        )
    }

    private fun List<DataMeeting>.mapToDomain(): List<DomainMeeting> {
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