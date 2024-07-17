package com.turitsynanton.android.wbtech.ui.screens.screenstate

import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.data.storage.models.Meeting


sealed class CommunitiesScreenState {
    data object Initial: CommunitiesScreenState()
    class Communities(val community: List<Community>): CommunitiesScreenState()
    class CommunityDetails(val community: Community, val meeting: List<Meeting>): CommunitiesScreenState()
}