package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
fun generateCommunitiesList(min: Int = 7, max: Int = 15): List<DataCommunity> {
    val communitiesList = List((min..max).random()) {
        DataCommunity(
            id = UUID.randomUUID().toString(),
            name = communitiesNames.random(),
            description = communityDescriptions.random(),
            icon = "",
            tags = generateTags(),
            users = generateUsersList(),
            events = generateEvents()
        )
    }
    return communitiesList
}