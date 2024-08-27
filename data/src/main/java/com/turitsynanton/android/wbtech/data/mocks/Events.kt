package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataHost
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainHost
import java.time.LocalDate
import java.time.Month
import java.util.UUID
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
fun generateEvents(): List<DataEvent> {
    val eventsList = List((10..30).random()) {
        DataEvent(
            id = UUID.randomUUID().toString(),
            name = generateRandomWord(5, 30),
            date = generateRandomDate().toString(),
            city = cities.random(),
            description = generateRandomWord(40, 80),
            host = DataUser(
                id = UUID.randomUUID().toString(),
                name = names.random(),
                description = generateRandomWord(40, 80),
                icon = "",
                tags = listOf(),
                city = cities.random()
            ),
            organizer = DataCommunity(
                id = UUID.randomUUID().toString(),
                name = generateRandomWord(5, 20),
                description = generateRandomWord(40, 80),
                icon = "",
                tags = listOf(),
                users = generateUsersList(),
                events = listOf()
            ),
            participants = generateUsersList(),
            tags = listOf(),
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
fun generateRandomDate(): LocalDate {
    val startYear = 1900
    val endYear = 2100

    val randomYear = Random.nextInt(startYear, endYear)
    val randomMonth = Month.of(Random.nextInt(1, 13))
    val randomDay = Random.nextInt(1, randomMonth.length(true))

    return LocalDate.of(randomYear, randomMonth, randomDay)
}