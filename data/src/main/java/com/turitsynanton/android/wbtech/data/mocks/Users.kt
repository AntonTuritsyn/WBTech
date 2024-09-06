package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import java.util.UUID

fun generateUsersList(min: Int = 1, max: Int = 10) : List<DataUser> {
    val usersList = List((min..max).random()) {
        DataUser(
            id = UUID.randomUUID().toString(),
            name = names.random(),
            city = cities.random(),
            description = usersDescriptions.random(),
            tags = generateTags(1, 5),
            icon = ""
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