package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCaseNew(
    private val dataListsRepository: IDataListsRepository
) {
    private val communitiesList = dataListsRepository.getCommunitiesListFlow()

    operator fun invoke(): Flow<List<DomainCommunity>> = communitiesList
}