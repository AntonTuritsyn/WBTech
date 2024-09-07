package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.InfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetCommunityIdByEventIdUseCaseNew(
    private val dataListsRepository: IDataListsRepository,
    private val getCommunitiesListUseCaseNew: GetCommunitiesListUseCaseNew,
) : IGetCommunityIdByEventIdUseCaseNew {

    private val id: MutableStateFlow<String> = MutableStateFlow("dfg")

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(eventId: String) {
        val comm = dataListsRepository.getCommunitiesListFlow() /*getCommunitiesListUseCaseNew.invoke()*/
        println("comm: $comm")
        println("ВЫПЛНЯЕТСЯ")
        comm.flatMapLatest { communities ->
            communities.firstOrNull { community ->
                community.events.any { it.id == eventId }
            }?.let { foundCommunity ->
                id.update { foundCommunity.id }
                println("foundCommunity.id: ${foundCommunity.id}")
            }
            emptyFlow<String>()
        }.launchIn(CoroutineScope(Dispatchers.IO))

        println("id: ${id.value}")
    }

    fun invoke(): Flow<String> = id
}
