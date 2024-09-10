package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataMyProfile
import java.util.UUID

fun generateProfile() = DataMyProfile(
    id = UUID.randomUUID().toString(),
    name = "Anton",
    city = cities.random(),
    phone = "+79999999999",
    description = usersDescriptions.random(),
    tags = generateTags(1, 5),
    icon = ""
)