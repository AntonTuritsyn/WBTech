package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
fun generateEvents(): List<DataEvent> {
    val eventsList = List((10..20).random()) {
        DataEvent(
            id = UUID.randomUUID().toString(),
            name = eventsNames.random(),
            date = generateRandomDate(),
            city = cities.random(),
            description = eventsDescriptions.random(),
            host = DataUser(
                id = UUID.randomUUID().toString(),
                name = names.random(),
                description = usersDescriptions.random(),
                icon = "",
                tags = listOf(),
                city = cities.random()
            ),
            /*organizer = DataCommunity(
                id = UUID.randomUUID().toString(),
                name = generateRandomWord(5, 20),
                description = generateRandomWord(40, 80),
                icon = "",
                tags = listOf(),
                users = generateUsersList(),
                events = listOf()
            ),*/
            participants = generateUsersList(),
            tags = generateTags(),
            icon = ""
        )
    }
    return eventsList
}

fun generateRandomWord(min: Int, max: Int): String {
    val chars = ('a'..'z') + ('A'..'Z') + (' ')
    return (1..(min..max).random())
        .map { chars.random() }
        .joinToString("")
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomDate(): String {
    val startYear = 2024
    val endYear = 2025

    val randomYear = Random.nextInt(startYear, endYear)
    val randomMonth = Month.of(Random.nextInt(1, 13))
    val randomDay = Random.nextInt(1, randomMonth.length(true))

    val randomDate = LocalDate.of(randomYear, randomMonth, randomDay)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    return randomDate.format(formatter)
}