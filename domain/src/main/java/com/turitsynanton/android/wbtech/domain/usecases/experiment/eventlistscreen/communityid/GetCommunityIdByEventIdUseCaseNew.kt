package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid

import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update

internal class GetCommunityIdByEventIdUseCaseNew(
    private val dataListsRepository: DataListsRepository,
) : IGetCommunityIdByEventIdUseCaseNew {

    private val id: MutableStateFlow<String> = MutableStateFlow("null")
    private val idFiltered = id // TODO доработать


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(eventId: String) {
        val comm = dataListsRepository.getCommunitiesListFlow()
        println("comm: $comm")

        comm.flatMapLatest { communities ->
            communities.firstOrNull { community ->
                community.events.any { it.id == eventId }
            }?.let { foundCommunity ->
                id.update { foundCommunity.id }
                println("foundCommunity.id: ${foundCommunity.id}")
            }
            emptyFlow<String>()
        }/*.launchIn(CoroutineScope(Dispatchers.IO))*/.flowOn(Dispatchers.IO)

        println("id: ${id.value}")
    }

    override fun invoke(): Flow<String> = idFiltered
}