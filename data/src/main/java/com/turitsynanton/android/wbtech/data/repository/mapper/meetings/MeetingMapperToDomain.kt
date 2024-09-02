package com.turitsynanton.android.wbtech.data.repository.mapper.meetings

import com.turitsynanton.android.wbtech.data.repository.mapper.IMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.meetingTags
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting

internal class MeetingMapperToDomain : IMapperToDomain<DataMeeting, DomainMeeting> {
    override fun mapToDomain(entity: DataMeeting): DomainMeeting {
        return DomainMeeting(
            id = entity.id,
            name = entity.name,
            date = entity.date,
            city = entity.city,
            ended = entity.ended,
            tags = meetingTags
        )
    }
}

internal fun DataMeeting.mapMeetingToDomain(mapper: IMapperToDomain<DataMeeting, DomainMeeting>): DomainMeeting {
    return mapper.mapToDomain(this)
}

internal fun List<DataMeeting>.mapMeetingToDomain(mapper: IMapperToDomain<DataMeeting, DomainMeeting>): List<DomainMeeting> {
    return map { it.mapMeetingToDomain(mapper) }
}