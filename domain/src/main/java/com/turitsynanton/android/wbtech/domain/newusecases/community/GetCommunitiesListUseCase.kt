package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

internal class GetCommunitiesListUseCase(private val /*communityRepository: CommunityRepository*/dataListsRepository: IDataListsRepository) :
    IGetCommunitiesListUseCase {
    override fun execute(): Flow<List<DomainCommunity>> =
        dataListsRepository.getCommunitiesListFlow()
}