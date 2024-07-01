package com.turitsynanton.android.wbtech.data

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
        "Developer meet",
        "11.12.2023",
        "Moscow",
        true,
        meetingTag
    ),
    Meeting(
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    )
    ,
    Meeting(
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    ),
    Meeting(
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    ),
    Meeting(
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTag
    ),
    Meeting(
        "LAST meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTag
    )
)