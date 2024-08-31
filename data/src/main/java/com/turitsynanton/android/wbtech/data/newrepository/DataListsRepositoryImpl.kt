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
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class DataListsRepositoryImpl(
    private val communityMapper: CommunityMapper,
    private val eventMapper: EventMapper
) : DataListsRepository {

    private val communitiesList = generateCommunitiesList()

    private val allEventsList = communitiesList.flatMap { it.events }

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(communitiesList) }.map { it.mapCommunityToDomain(communityMapper) }

    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity?> =
        flow {
            emit(communitiesList.find { it.id == comunityId }
                ?.mapCommunityToDomain(communityMapper))
        }

    override fun getEventsListFlow(): Flow<List<DomainEvent>> =
        flow { emit(allEventsList) }.map { it.mapEventToDomain(eventMapper) }

    override fun getEventDetailsFlow(eventId: String): Flow<DomainEvent> =
        flow {
            allEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { emit(it) }
        }
}