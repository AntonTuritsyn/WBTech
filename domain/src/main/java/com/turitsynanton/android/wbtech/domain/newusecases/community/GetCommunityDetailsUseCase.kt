package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.CommunityRepository
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityDetailsUseCase(private val dataListsRepository: DataListsRepository) :
    IGetCommunityDetailsUseCase {

    override fun execute(comunityId: String): Flow<DomainCommunity?> =
        dataListsRepository.getCommunityDetailsFlow(comunityId)
}