package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.models.DataHost
import java.util.UUID

fun generateHost(): DataHost {
    val host = DataHost(
        id = UUID.randomUUID().toString(),
        name = names.random(),
        description = generateRandomWord(40, 80),
        icon = ""
    )
    return host
}