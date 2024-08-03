package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityDetailsUseCase(private val communityRepository: CommunityRepository) :
    IGetCommunityDetailsUseCase {

    override fun execute(comunityId: String): Flow<DomainCommunity?> =
        communityRepository.getCommunityDetailsFlow(comunityId)
}