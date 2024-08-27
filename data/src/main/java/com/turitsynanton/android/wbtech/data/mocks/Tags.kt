package com.turitsynanton.android.wbtech.data.mocks

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import java.util.UUID

fun generateTagsForEvent(min: Int = 1, max: Int = 4): List<DataTag> {
    val shuffledTags = tags.shuffled().take((min..max).random())
    val tagsList = shuffledTags.map {
        DataTag(
            id = UUID.randomUUID().toString(),
            content = it
        )
    }
    return tagsList
}
