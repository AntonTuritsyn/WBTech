package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCommunityListUseCaseStub : IGetCommunityListUseCase {
    private var communityList: List<DomainCommunity> = emptyList()

    fun setCommunityList(communities: List<DomainCommunity>) {
        communityList = communities
    }

    override fun execute(): Flow<List<DomainCommunity>> {
        return flowOf(communityList)
    }
}