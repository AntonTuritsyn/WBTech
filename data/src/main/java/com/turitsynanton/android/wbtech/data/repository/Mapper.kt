package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting

internal fun DataCommunity.mapCommunityToDomain(): DomainCommunity {
    return DomainCommunity(
        id = id,
        name = name,
        size = size
    )
}

internal fun List<DataCommunity>.mapCommunityToDomain(): List<DomainCommunity> {
    return map { it.mapCommunityToDomain() }
}

internal fun DataMeeting.mapMeetingToDomain() : DomainMeeting {
    return DomainMeeting(
        id = id,
        name = name,
        date = date,
        city = city,
        ended = ended,
        tags = meetingTags
    )
}

internal fun List<DataMeeting>.mapMeetingToDomain(): List<DomainMeeting> {
    return map { it.mapMeetingToDomain() }
}