package com.turitsynanton.android.wbtech.domain.usecases.community.list

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCase(private val /*communityRepository: CommunityRepository*/dataListsRepository: DataListsRepository) :
    IGetCommunitiesListUseCase {
    override fun execute(): Flow<List<DomainCommunity>> =
        dataListsRepository.getCommunitiesListFlow()
}