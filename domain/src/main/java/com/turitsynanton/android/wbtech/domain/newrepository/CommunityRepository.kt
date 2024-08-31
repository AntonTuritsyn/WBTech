package com.turitsynanton.android.wbtech.domain.newrepository

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getCommunityDetailsFlow(comunityId: String) : Flow<DomainCommunity?>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>
}