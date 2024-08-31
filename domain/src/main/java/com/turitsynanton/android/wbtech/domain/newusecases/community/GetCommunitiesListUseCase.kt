package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.CommunityRepository
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCase(private val /*communityRepository: CommunityRepository*/dataListsRepository: DataListsRepository) :
    IGetCommunitiesListUseCase {
    override fun execute(): Flow<List<DomainCommunity>> =
        dataListsRepository.getCommunitiesListFlow()
}