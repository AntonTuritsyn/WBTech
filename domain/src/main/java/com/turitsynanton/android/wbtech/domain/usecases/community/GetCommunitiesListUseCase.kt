package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCase(private val /*communityRepository: CommunityRepository*/dataListsRepository: IDataListsRepository) :
    IGetCommunitiesListUseCase {
    override fun execute(): Flow<List<DomainCommunity>> =
        dataListsRepository.getCommunitiesListFlow()
}