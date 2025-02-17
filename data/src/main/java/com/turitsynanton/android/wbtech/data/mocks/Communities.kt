package com.turitsynanton.android.wbtech.data.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataEvent
import com.turitsynanton.android.wbtech.data.storage.models.DataTag
import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
fun generateCommunitiesList(min: Int = 7, max: Int = 15): List<DataCommunity> {
    val availableCommunitiesLinks = communitiesLinks.toMutableList()
    val communitiesList = List((min..max).random()) {
        val randomLink = availableCommunitiesLinks.random()
        availableCommunitiesLinks.remove(randomLink)
        val dataCommunity = DataCommunity(
            id = UUID.randomUUID().toString(),
            name = communitiesNames.random(),
            description = communityDescriptions.random(),
            icon = randomLink,
            tags = generateTags(),
            users = usersForCommunities(),
            events = generateEvents()
        )
        dataCommunity
    }
    return communitiesList
}