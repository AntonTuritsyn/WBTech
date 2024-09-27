package com.turitsynanton.android.wbtech.domain.usecases.community.details

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityDetailsUseCase(private val dataListsRepository: DataListsRepository) :
    IGetCommunityDetailsUseCase {

    override fun execute(communityId: String): Flow<DomainCommunity> =
        dataListsRepository.getCommunityDetailsFlow(communityId)
}