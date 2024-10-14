package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

internal class GetCommunityIdByEventIdUseCase(private val dataListsRepository: DataListsRepository) :
    IGetCommunityIdByEventIdUseCase {
    override fun execute(
        eventId: String
    ): Flow<DomainCommunity?> {
        val communities = dataListsRepository.getCommunitiesListFlow()
        val communityDetails = MutableStateFlow<DomainCommunity?>(null)
        communities.flatMapLatest { communities ->
            communities.firstOrNull { community ->
                community.events.any { it.id == eventId }
            }?.let { neededCommunity ->
                communityDetails.update {
                    neededCommunity
                }
            }
            emptyFlow<String>()
        }.launchIn(CoroutineScope(Dispatchers.IO))

        return communityDetails
    }
}
