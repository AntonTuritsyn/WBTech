package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CommunityRepositoryImpl : CommunityRepository {
    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity?> =
        flow { emit(dataCommunities.find { it.id == comunityId }?.mapCommunityToDomain()) }

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(dataCommunities) }.map { it.mapCommunityToDomain() }
}