package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.models.DataEvent
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
fun generateEvents(min: Int = 10, max: Int = 20): List<DataEvent> {
    var availableEventsLinks = eventsLinks.toMutableList()
    val eventsList = List((min..max).random()) {
        val randomLink = availableEventsLinks.random()
        if (availableEventsLinks.isNotEmpty()) {
            availableEventsLinks.remove(randomLink)
        } else {
            availableEventsLinks = eventsLinks.toMutableList()
        }
        DataEvent(
            id = UUID.randomUUID().toString(),
            name = eventsNames.random(),
            date = generateRandomDate(),
            city = cities.random(),
            description = eventsDescriptions.random(),
            host = usersForEvents().random(),
            participants = usersForEvents(),
            tags = generateTags(),
            icon = randomLink
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