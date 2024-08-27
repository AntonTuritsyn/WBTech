package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import java.util.UUID

fun generateUsersList() : List<DataUser> {
    val usersList = List((5..10).random()) {
        DataUser(
            id = UUID.randomUUID().toString(),
            name = names.random(),
            city = cities.random(),
            description = generateRandomWord(40, 80),
            tags = listOf(),
            icon = ""
        )
    }
    return usersList
}