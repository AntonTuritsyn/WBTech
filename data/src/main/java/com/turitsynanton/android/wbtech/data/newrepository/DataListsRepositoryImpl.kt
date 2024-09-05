package com.turitsynanton.android.wbtech.data.newrepository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.CommunityMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapCommunityToDomain
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapEventToDomain
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class DataListsRepositoryImpl(
    private val communityMapper: CommunityMapper,
    private val eventMapper: EventMapper
) : IDataListsRepository {

    private val communitiesList = generateCommunitiesList()

    private val allEventsList = communitiesList.flatMap { it.events }

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(communitiesList) }.map { it.mapCommunityToDomain(communityMapper) }

    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity> =
        flow {
            communitiesList.find { it.id == comunityId }?.let {
                emit(
                    it
                        .mapCommunityToDomain(communityMapper))
            }
        }

    override fun getEventsListFlow(): Flow<List<DomainEvent>> =
        flow { emit(allEventsList) }.map { it.mapEventToDomain(eventMapper) }

    override fun getEventDetailsFlow(eventId: String): Flow<DomainEvent> =
        flow {
            allEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { emit(it) }
        }

    override fun getEventDetailsFlowExperiment(eventId: String): Flow<DomainEvent?> =
        flow { allEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { emit(it) } }

    override fun getEventDetailsFlowNew(eventId: String): DomainEvent? =
        allEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { it }


}