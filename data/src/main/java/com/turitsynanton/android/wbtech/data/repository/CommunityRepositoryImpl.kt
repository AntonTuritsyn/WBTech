package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CommunityRepositoryImpl : CommunityRepository {

    override fun getCommunitiesList(): Flow<List<DomainCommunity>> =
        flow { emit(dataCommunities) }.map { it.mapToDomain() }

    override fun getCommunityDetails(comunityId: Long): DomainCommunity? =
        dataCommunities.find { it.id == comunityId }?.mapToDomain()

    private fun DataCommunity.mapToDomain(): DomainCommunity {
        return DomainCommunity(
            id = id,
            name = name,
            size = size
        )
    }

    private fun List<DataCommunity>.mapToDomain(): List<DomainCommunity> {
        return map { it.mapToDomain() }
    }
}