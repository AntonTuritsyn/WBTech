package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import java.util.UUID

fun generateUsersList(min: Int = 1, max: Int = 10) : List<DataUser> {
    val usersList = List((min..max).random()) {
        DataUser(
            id = UUID.randomUUID().toString(),
            name = names.random(),
            city = cities.random(),
            phone = "",
            description = usersDescriptions.random(),
            tags = generateTags(1, 5),
            icon = avatarLinks.random()
        )
    }
    return usersList
}

fun usersForEvents(): List<DataUser> {
    return mainUsersList.shuffled().take((1..10).random())
}

fun usersForCommunities(): List<DataUser> {
    return mainUsersList.shuffled().take((10..20).random())
}