package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.GetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

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
        getCommunityIdByEventIdUseCaseNew.invoke() // TODO фильтр в usecase
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