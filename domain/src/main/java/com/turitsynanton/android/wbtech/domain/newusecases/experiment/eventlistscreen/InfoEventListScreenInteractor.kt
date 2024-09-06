package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.GetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class InfoEventListScreenInteractor(
    private val getEventsListUseCaseNew: GetEventsListUseCaseNew,
    private val filterEventUseCaseNew: FilterEventUseCaseNew,
    private val getCommunitiesListUseCaseNew: GetCommunitiesListUseCaseNew,
    private val getCommunityIdByEventIdUseCaseNew: GetCommunityIdByEventIdUseCaseNew
): IInfoEventListScreenInteractor {

    private val innerFlow = combine(
        getEventsListUseCaseNew(),
        getCommunitiesListUseCaseNew(),
        filterEventUseCaseNew.invoke(),
        getCommunityIdByEventIdUseCaseNew.invoke()
    ) { eventList, communitiesList, filteredEvents, communityId ->
        CombinedEventListScreenInfo(
            eventList = eventList,
            communitiesList = communitiesList,
            filteredEvents = filteredEvents,
            communityId = communityId
        )
    }
    override fun invoke(): Flow<CombinedEventListScreenInfo> = innerFlow
}

data class CombinedEventListScreenInfo(
    val eventList: List<DomainEvent>,
    val communitiesList: List<DomainCommunity>,
    val filteredEvents: List<DomainEvent>,
    val communityId: String
)