package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository

class GetCommunityDetailsUseCase(private val communityRepository: CommunityRepository) {
    fun execute(communityId: Long): DomainCommunity? =
        communityRepository.getCommunityDetails(communityId)
}