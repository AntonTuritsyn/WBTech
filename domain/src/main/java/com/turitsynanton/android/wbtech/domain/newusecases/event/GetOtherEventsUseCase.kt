package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

internal class GetOtherEventsUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetOtherEventsUseCase {
    override fun execute(eventId: String): Flow<List<DomainEvent>> {
        val communities = dataListsRepository.getCommunitiesListFlow()
        val otherEvents = MutableStateFlow<List<DomainEvent>>(emptyList())

        communities.flatMapLatest { communities ->
            communities.firstOrNull { community ->
                community.events.any { it.id == eventId }
            }?.let { foundCommunity ->
                otherEvents.update { foundCommunity.events}
                println("foundCommunity.id: ${foundCommunity.events}")
            }
            emptyFlow<String>()
        }.launchIn(CoroutineScope(Dispatchers.IO))

        return otherEvents
    }
}