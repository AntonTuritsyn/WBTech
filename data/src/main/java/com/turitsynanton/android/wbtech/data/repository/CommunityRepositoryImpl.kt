package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.data.repository.mapper.communities.CommunityMapper
import com.turitsynanton.android.wbtech.data.repository.mapper.communities.mapCommunityToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CommunityRepositoryImpl(private val mapper: CommunityMapper) : CommunityRepository {
    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity?> =
        flow { emit(dataCommunities.find { it.id == comunityId }?.mapCommunityToDomain(mapper)) }

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(dataCommunities) }.map { it.mapCommunityToDomain(mapper) }
}