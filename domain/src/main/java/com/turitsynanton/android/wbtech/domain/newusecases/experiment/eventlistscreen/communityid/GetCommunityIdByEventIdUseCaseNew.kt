package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetCommunityIdByEventIdUseCaseNew(
    private val dataListsRepository: IDataListsRepository,
    private val getCommunitiesListUseCaseNew: GetCommunitiesListUseCaseNew
) : IGetCommunityIdByEventIdUseCaseNew {

    val id: MutableStateFlow<String> = MutableStateFlow("dfg")

    override fun execute(eventId: String, communities: List<DomainCommunity>) {

        communities.forEach { community ->
            if (community.events.any { event ->
                    event.id == eventId
                }) {
                id.tryEmit(eventId)
            }
        }
    }

    fun invoke(): Flow<String> = id
}
