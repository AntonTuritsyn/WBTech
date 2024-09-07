package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataProfile
import java.util.UUID

fun generateProfile() = DataProfile(
    id = UUID.randomUUID().toString(),
    name = "Anton",
    city = cities.random(),
    description = usersDescriptions.random(),
    tags = generateTags(1, 5),
    icon = ""
)