package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getCommunityDetailsFlow(communityId: String) : Flow<DomainCommunity?>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>
}