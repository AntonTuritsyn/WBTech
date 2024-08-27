package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
fun generateCommunitiesList(min: Int = 5, max: Int = 10): List<DataCommunity> {
    val commmunitiesList = List((min..max).random()) {
        DataCommunity(
            id = UUID.randomUUID().toString(),
            name = generateRandomWord(5, 20),
            description = generateRandomWord(40, 80),
            icon = "",
            tags = listOf(),
            users = generateUsersList(),
            events = generateEvents()
        )
    }
    return commmunitiesList
}