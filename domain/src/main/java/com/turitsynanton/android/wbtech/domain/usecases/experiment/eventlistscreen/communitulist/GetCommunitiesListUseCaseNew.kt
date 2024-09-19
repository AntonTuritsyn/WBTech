package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communitulist

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCaseNew(
    private val dataListsRepository: IDataListsRepository
) {
    private val communitiesList = dataListsRepository.getCommunitiesListFlow()

    operator fun invoke(): Flow<List<DomainCommunity>> = communitiesList
}