package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.models.DomainProfile
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IDataListsRepository {

    fun getCommunityDetailsFlow(comunityId: String) : Flow<DomainCommunity>

    fun getCommunitiesListFlow() : Flow<List<DomainCommunity>>

    fun getEventsListFlow() : Flow<List<DomainEvent>>

    fun getEventDetailsFlow(eventId: String) : Flow<DomainEvent>

    fun getEventDetailsFlowExperiment(eventId: String) : Flow<DomainEvent?>

    fun getEventDetailsFlowNew(eventId: String) : DomainEvent?

    fun getUsersListFlow() : Flow<List<DomainUser>>

    fun getProfileFlow() : Flow<DomainUser>

}