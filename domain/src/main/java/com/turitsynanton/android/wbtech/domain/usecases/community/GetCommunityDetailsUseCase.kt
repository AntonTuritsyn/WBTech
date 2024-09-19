package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityDetailsUseCase(private val dataListsRepository: IDataListsRepository) :
    IGetCommunityDetailsUseCase {

    override fun execute(comunityId: String): Flow<DomainCommunity> =
        dataListsRepository.getCommunityDetailsFlow(comunityId)
}