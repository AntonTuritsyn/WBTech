package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface DataListsRepository {

    fun getCommunityDetailsFlow(communityId: String): Flow<DomainCommunity>
    fun getCommunitiesListFlow(): Flow<List<DomainCommunity>>
    fun updateCommunitiesList(community: DomainCommunity)

    fun getEventDetailsFlowExperiment(eventId: String): Flow<DomainEvent?>

    fun getEventDetailsFlowNew(eventId: String): DomainEvent?

    fun getUsersListFlow(): Flow<List<DomainUser>>

    fun getProfileFlow(): Flow<DomainUser>

    fun getMyProfileFlow(): Flow<DomainUser>
}
