package com.turitsynanton.android.wbtech.data.newrepository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.mocks.generateProfile
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.data.mocks.mainUsersList
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.CommunityMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.ProfileMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.UsersMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapCommunityToDomain
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapEventToDomain
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapProfileToDomain
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapUserToDomain
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile
import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class DataListsRepositoryImpl(
    private val communityMapper: CommunityMapper,
    private val eventMapper: EventMapper,
    private val userMapper: UsersMapper,
    private val profileMapper: ProfileMapper
) : IDataListsRepository {

    private val communitiesList = generateCommunitiesList()

    private val usersList = mainUsersList

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

    override fun getUsersListFlow(): Flow<List<DomainUser>> =
        flow {emit(usersList)}.map { it.mapUserToDomain(userMapper) }

    override fun getProfileFlow(): Flow<DomainProfile> =
        flow { emit(generateProfile()) }.map { it.mapProfileToDomain(profileMapper) }
}