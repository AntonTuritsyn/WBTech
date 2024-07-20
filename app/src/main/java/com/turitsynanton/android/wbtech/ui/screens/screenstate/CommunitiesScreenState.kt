package com.turitsynanton.android.wbtech.ui.screens.screenstate


sealed class CommunitiesScreenState {
    data object Initial: CommunitiesScreenState()
    class Communities(val dataCommunity: List<com.turitsynanton.android.wbtech.data.storage.models.DataCommunity>): CommunitiesScreenState()
    class CommunityDetails(val dataCommunity: com.turitsynanton.android.wbtech.data.storage.models.DataCommunity, val dataMeeting: List<com.turitsynanton.android.wbtech.data.storage.models.DataMeeting>): CommunitiesScreenState()
}