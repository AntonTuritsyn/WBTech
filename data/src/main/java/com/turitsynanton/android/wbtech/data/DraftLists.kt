package com.turitsynanton.android.wbtech.data

import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.data.storage.models.DataMeetingTag

val tabs1 = listOf("Все встречи", "Активные")
val tabs2 = listOf("Запланировано", "Уже прошли")
internal val dataMeetingTags = listOf(
    DataMeetingTag(
        "Java"
    ),
    DataMeetingTag(
        "Kotlin"
    ),
    DataMeetingTag(
        "Android"
    )
)

internal val dataMeetings = listOf(
    DataMeeting(
        "1",
        "Developer meet",
        "11.12.2023",
        "Moscow",
        true,
        dataMeetingTags
    ),
    DataMeeting(
        "2",
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        dataMeetingTags
    ),
    DataMeeting(
        "3",
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        dataMeetingTags
    )
    ,
    DataMeeting(
        "4",
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        dataMeetingTags
    ),
    DataMeeting(
        "5",
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        dataMeetingTags
    ),
    DataMeeting(
        "6",
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        dataMeetingTags
    ),
    DataMeeting(
        "7",
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        dataMeetingTags
    ),
    DataMeeting(
        "8",
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        dataMeetingTags
    ),
    DataMeeting(
        "9",
        "LAST meet",
        "11.08.2024",
        "Kazan",
        false,
        dataMeetingTags
    )
)

internal val dataCommunities = listOf(
    DataCommunity(
        "1",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "2",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "3",
        "Kotlin comm",
        "19348275",
    ),
    DataCommunity(
        "4",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "5",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "6",
        "Kotlin comm",
        "19348275",
    ),
    DataCommunity(
        "7",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "8",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "9",
        "Kotlin comm",
        "19348275",
    ),
    DataCommunity(
        "10",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "11",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "12",
        "Kotlin comm",
        "19348275",
    ),
    DataCommunity(
        "13",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "14",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "15",
        "Kotlin comm",
        "19348275",
    ),
    DataCommunity(
        "16",
        "Developer comm",
        "10112",
    ),
    DataCommunity(
        "17",
        "Android comm",
        "10",
    ),
    DataCommunity(
        "16",
        "LAST",
        "19348275",
    )
)