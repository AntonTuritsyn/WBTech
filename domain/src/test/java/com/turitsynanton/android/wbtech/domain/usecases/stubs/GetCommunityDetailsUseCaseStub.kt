package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class GetCommunityDetailsUseCaseStub : IGetCommunityDetailsUseCase {
    private var communityDetails: Map<String, DomainCommunity?> = emptyMap()

    fun setCommunityDetails(details: Map<String, DomainCommunity?>) {
        communityDetails = details
    }

    override fun execute(comunityId: String): Flow<DomainCommunity?> {
        return flowOf(communityDetails[comunityId])
    }
}