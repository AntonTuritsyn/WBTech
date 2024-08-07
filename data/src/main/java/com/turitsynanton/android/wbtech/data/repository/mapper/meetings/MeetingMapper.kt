package com.turitsynanton.android.wbtech.data.repository.mapper.meetings

import com.turitsynanton.android.wbtech.data.repository.mapper.IMapper
import com.turitsynanton.android.wbtech.data.repository.meetingTags
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting

internal class MeetingMapper : IMapper<DataMeeting, DomainMeeting> {
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

internal fun DataMeeting.mapMeetingToDomain(mapper: IMapper<DataMeeting, DomainMeeting>): DomainMeeting {
    return mapper.mapToDomain(this)
}

internal fun List<DataMeeting>.mapMeetingToDomain(mapper: IMapper<DataMeeting, DomainMeeting>): List<DomainMeeting> {
    return map { it.mapMeetingToDomain(mapper) }
}