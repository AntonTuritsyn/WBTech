package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventsListByCommunityIdUseCase(
    private val dataListsRepository: DataListsRepository
): IEventsListByCommunityIdUseCase {
    override fun execute(communityId: String): Flow<List<DomainEvent>> {
        return dataListsRepository.getCommunitiesListFlow().map { communityList ->
            communityList.find { it.id == communityId }?.events ?: emptyList()
        }
    }
}