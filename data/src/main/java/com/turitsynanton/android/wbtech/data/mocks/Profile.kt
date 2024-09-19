package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.models.DataMyProfile
import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import java.util.UUID

fun generateProfile() = DataUser(
    id = UUID.randomUUID().toString(),
    name = "Anton",
    city = cities.random(),
    phone = "+79999999999",
    description = usersDescriptions.random(),
    tags = generateTags(1, 5),
    icon = ""
)