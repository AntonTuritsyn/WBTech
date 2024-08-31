package com.turitsynanton.android.wbtech.domain.newrepository

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface DataListsRepository {

    fun getCommunityDetailsFlow(comunityId: String) : Flow<DomainCommunity?>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>

    fun getEventsListFlow() : Flow<List<DomainEvent>>

    fun getEventDetailsFlow(eventId: String) : Flow<DomainEvent>
}