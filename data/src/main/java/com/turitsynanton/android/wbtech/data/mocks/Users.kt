package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import java.util.UUID

fun generateUsersList(min: Int = 1, max: Int = 10) : List<DataUser> {
    val availableAvatarLinks = avatarLinks.toMutableList()
    val usersList = List((min..max).random()) {
        val randomLink = availableAvatarLinks.random()
        availableAvatarLinks.remove(randomLink)
        DataUser(
            id = UUID.randomUUID().toString(),
            name = names.random(),
            city = cities.random(),
            phone = "",
            description = usersDescriptions.random(),
            tags = generateTags(1, 5),
            icon = randomLink
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