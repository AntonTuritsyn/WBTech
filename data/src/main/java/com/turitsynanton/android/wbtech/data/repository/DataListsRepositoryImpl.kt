package com.turitsynanton.android.wbtech.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.mocks.generateProfile
import com.turitsynanton.android.wbtech.data.mocks.mainUsersList
import com.turitsynanton.android.wbtech.data.mocks.myProfile
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.CommunityMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.EventMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.ProfileMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.UsersMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapCommunityToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapEventToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapProfileToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapUserToDomain
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.DataEvent
import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.models.DomainProfile
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class DataListsRepositoryImpl(
    private val communityMapper: CommunityMapperToDomain,
    private val eventMapper: EventMapperToDomain,
    private val userMapper: UsersMapperToDomain,
    private val profileMapper: ProfileMapperToDomain
) : IDataListsRepository {

    private val generatedCommunitiesList = generateCommunitiesList()
    private val generatedProfile = /*generateProfile()*/myProfile
    private val generatedAllEventsList = generatedCommunitiesList.flatMap { it.events }

    private val _communitiesList = MutableStateFlow<List<DataCommunity>>(generatedCommunitiesList)
    private val communitiesList = _communitiesList.asStateFlow()
    private val communityMap = communitiesList.value.associateBy { it.id }

    private val _usersList = MutableStateFlow<List<DataUser>>(mainUsersList)
    private val usersList = _usersList.asStateFlow()
    private val userMap = usersList.value.associateBy { it.id }

    private val _allEventsList = MutableStateFlow<List<DataEvent>>(generatedAllEventsList)
    private val allEventsList = _allEventsList.asStateFlow()
    private val eventMap = allEventsList.value.associateBy { it.id }

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        communitiesList.map { it.mapCommunityToDomain(communityMapper) }

    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity> =
        flow { communityMap[comunityId]?.mapCommunityToDomain(communityMapper)?.let { emit(it) } }
        /*flow {
            generatedCommunitiesList.find { it.id == comunityId }?.let {
                emit(it.mapCommunityToDomain(communityMapper))
            }
        }*/

    override fun getEventsListFlow(): Flow<List<DomainEvent>> {
        println(generatedAllEventsList.size)
        return flow { emit(generatedAllEventsList) }.map { it.mapEventToDomain(eventMapper) }
    }

    override fun getEventDetailsFlow(eventId: String): Flow<DomainEvent> =
        flow {
            generatedAllEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { emit(it) }
        }

    override fun getEventDetailsFlowExperiment(eventId: String): Flow<DomainEvent?> =
        flow {
            /*generatedAllEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { emit(it) }*/
        }

    override fun getEventDetailsFlowNew(eventId: String): DomainEvent? =
        generatedAllEventsList.find { it.id == eventId }?.mapEventToDomain(eventMapper)?.let { it }

    override fun getUsersListFlow(): Flow<List<DomainUser>> =
//        flow {emit(usersList)}.map { it.mapUserToDomain(userMapper) }
        _usersList.map { it.mapUserToDomain(userMapper) }

    override fun getProfileFlow(): Flow<DomainUser> =
        flow { emit(generatedProfile) }.map { it.mapUserToDomain(userMapper) }
}