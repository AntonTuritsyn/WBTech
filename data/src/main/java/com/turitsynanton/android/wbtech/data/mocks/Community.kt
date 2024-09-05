package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import java.util.UUID

fun generateCommunity(): DataCommunity {
    val commmunity = DataCommunity(
        id = UUID.randomUUID().toString(),
        name = generateRandomWord(5, 20),
        description = generateRandomWord(40, 80),
        icon = "",
        tags = generateTags(),
        users = generateUsersList(),
        events = generateEvents()
    )
    return commmunity
}