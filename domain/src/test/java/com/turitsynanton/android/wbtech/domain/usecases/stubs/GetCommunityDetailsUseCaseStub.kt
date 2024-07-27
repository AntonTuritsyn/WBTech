package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class GetCommunityDetailsUseCaseStub : IGetCommunityDetailsUseCase {
    private var communityDetails: Map<Long, DomainCommunity?> = emptyMap()

    fun setCommunityDetails(details: Map<Long, DomainCommunity?>) {
        communityDetails = details
    }

    override fun execute(comunityId: Long): Flow<DomainCommunity?> {
        return flowOf(communityDetails[comunityId])
    }
}