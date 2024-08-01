package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityListUseCase(private val communityRepository: CommunityRepository) :
    IGetCommunityListUseCase {

    override fun execute(): Flow<List<DomainCommunity>> =
        communityRepository.getCommunitiesListFlow()
}