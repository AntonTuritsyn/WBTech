package com.turitsynanton.android.wbtech.domain.newrepository

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile
import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import kotlinx.coroutines.flow.Flow

interface IDataListsRepository {

    fun getCommunityDetailsFlow(comunityId: String) : Flow<DomainCommunity>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>

    fun getEventsListFlow() : Flow<List<DomainEvent>>

    fun getEventDetailsFlow(eventId: String) : Flow<DomainEvent>

    fun getEventDetailsFlowExperiment(eventId: String) : Flow<DomainEvent?>

    fun getEventDetailsFlowNew(eventId: String) : DomainEvent?

    fun getUsersListFlow() : Flow<List<DomainUser>>

}