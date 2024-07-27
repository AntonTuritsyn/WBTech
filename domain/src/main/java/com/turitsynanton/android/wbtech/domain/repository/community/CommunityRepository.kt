package com.turitsynanton.android.wbtech.domain.repository.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getCommunityDetailsFlow(comunityId: Long) : Flow<DomainCommunity?>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>
}