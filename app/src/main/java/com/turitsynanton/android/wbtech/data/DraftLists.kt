package com.turitsynanton.android.wbtech.data

import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.data.storage.models.Meeting
import com.turitsynanton.android.wbtech.data.storage.models.MeetingTag

val tabs1 = listOf("Все встречи", "Активные")
val tabs2 = listOf("Запланировано", "Уже прошли")
val meetingTag = listOf(
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

val meetings = listOf(
    Meeting(
        1,
        "Developer meet",
        "11.12.2023",
        "Moscow",
        true,
        meetingTag
    ),
    Meeting(
        2,
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        3,
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    )
    ,
    Meeting(
        4,
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        5,
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    ),
    Meeting(
        6,
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        7,
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    ),
    Meeting(
        8,
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        9,
        "LAST meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    )
)

val communities = listOf(
    Community(
        1,
        "Developer comm",
        "10112",
    ),
    Community(
        2,
        "Android comm",
        "10",
    ),
    Community(
        3,
        "Kotlin comm",
        "19348275",
    ),
    Community(
        4,
        "Developer comm",
        "10112",
    ),
    Community(
        5,
        "Android comm",
        "10",
    ),
    Community(
        6,
        "Kotlin comm",
        "19348275",
    ),
    Community(
        7,
        "Developer comm",
        "10112",
    ),
    Community(
        8,
        "Android comm",
        "10",
    ),
    Community(
        9,
        "Kotlin comm",
        "19348275",
    ),
    Community(
        10,
        "Developer comm",
        "10112",
    ),
    Community(
        11,
        "Android comm",
        "10",
    ),
    Community(
        12,
        "Kotlin comm",
        "19348275",
    ),
    Community(
        13,
        "Developer comm",
        "10112",
    ),
    Community(
        14,
        "Android comm",
        "10",
    ),
    Community(
        15,
        "Kotlin comm",
        "19348275",
    ),
    Community(
        16,
        "Developer comm",
        "10112",
    ),
    Community(
        17,
        "Android comm",
        "10",
    ),
    Community(
        16,
        "LAST",
        "19348275",
    )
)