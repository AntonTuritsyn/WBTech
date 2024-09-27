package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communitulist

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesListUseCaseNew(
    dataListsRepository: DataListsRepository
) {
    private val communitiesList = dataListsRepository.getCommunitiesListFlow()

    operator fun invoke(): Flow<List<DomainCommunity>> {

        return communitiesList
    }
}