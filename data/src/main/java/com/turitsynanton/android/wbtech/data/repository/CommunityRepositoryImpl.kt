package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.Community
import com.turitsynanton.android.wbtech.domain.models.Meeting
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import com.turitsynanton.android.wbtech.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CommunityRepositoryImpl : CommunityRepository {

    override fun getCommunitiesList(): Flow<List<Community>> =
        flow { emit(dataCommunities) }.map { it.mapToDomain() }

    override fun getCommunityDetails(comunityId: Long): Community? =
        dataCommunities.find { it.id == comunityId }?.mapToDomain()

    private fun DataCommunity.mapToDomain(): Community {
        return Community(
            id = id,
            name = name,
            size = size
        )
    }

    private fun List<DataCommunity>.mapToDomain(): List<Community> {
        return map { it.mapToDomain() }
    }
}