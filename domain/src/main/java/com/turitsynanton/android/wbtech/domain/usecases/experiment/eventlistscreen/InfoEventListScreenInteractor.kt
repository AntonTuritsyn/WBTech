package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid.GetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

internal class InfoEventListScreenInteractor(
    getEventsListUseCaseNew: GetEventsListUseCaseNew,
    filterEventUseCaseNew: FilterEventUseCaseNew,
    getCommunitiesListUseCaseNew: GetCommunitiesListUseCaseNew,
    getCommunityIdByEventIdUseCaseNew: GetCommunityIdByEventIdUseCaseNew
): IInfoEventListScreenInteractor {

    private val innerFlow = combine(
        getEventsListUseCaseNew(),
        getCommunitiesListUseCaseNew(),
        filterEventUseCaseNew(),
        getCommunityIdByEventIdUseCaseNew.invoke()
    ) { eventList, communitiesList, filteredEvents, communityId ->
        CombinedEventListScreenInfo(
            eventList = eventList,
            communitiesList = communitiesList,
            filteredEvents = filteredEvents,
            communityId = communityId
        )
    }.flowOn(Dispatchers.IO) // TODO проверить

    override fun invoke(): Flow<CombinedEventListScreenInfo> = innerFlow
}

data class CombinedEventListScreenInfo(
    val eventList: List<DomainEvent>,
    val communitiesList: List<DomainCommunity>,
    val filteredEvents: List<DomainEvent>,
    val communityId: String
)