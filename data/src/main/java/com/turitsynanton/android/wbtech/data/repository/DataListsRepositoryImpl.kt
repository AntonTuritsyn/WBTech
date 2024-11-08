package com.turitsynanton.android.wbtech.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generatedAllEventsList
import com.turitsynanton.android.wbtech.data.mocks.generatedCommunitiesList
import com.turitsynanton.android.wbtech.data.mocks.generatedProfile
import com.turitsynanton.android.wbtech.data.mocks.mainUsersList
import com.turitsynanton.android.wbtech.data.repository.mappertodata.CommunityMapperToData
import com.turitsynanton.android.wbtech.data.repository.mappertodata.mapCommunityToData
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.CommunityMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.EventMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.UsersMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapCommunityToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapEventToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapUserToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@RequiresApi(Build.VERSION_CODES.O)
internal class DataListsRepositoryImpl(
    private val communityMapperToDomain: CommunityMapperToDomain,
    private val eventMapper: EventMapperToDomain,
    private val userMapper: UsersMapperToDomain,
    private val communityMapperToData: CommunityMapperToData
) : DataListsRepository {

    private val _communitiesList = MutableStateFlow(generatedCommunitiesList)
    private val communitiesList = _communitiesList.asStateFlow()
    private val communityMap = communitiesList.value.associateBy { it.id }

    private val _usersList = MutableStateFlow(mainUsersList)
    private val usersList = _usersList.asStateFlow()
    private val userMap = usersList.value.associateBy { it.id }

    private val _allEventsList = MutableStateFlow(generatedAllEventsList)
    private val allEventsList = _allEventsList.asStateFlow()
    private val eventMap = allEventsList.value.associateBy { it.id }
    private val myProfile = generatedProfile

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        communitiesList.map { it.mapCommunityToDomain(communityMapperToDomain) }

    override fun getCommunityDetailsFlow(communityId: String): Flow<DomainCommunity> =
        flow {
            _communitiesList.value.firstOrNull { it.id == communityId }?.let {
                emit(it.mapCommunityToDomain(communityMapperToDomain))
            }
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

    override fun getMyProfileFlow(): Flow<DomainUser> {
        return flow { emit(myProfile) }.map { it.mapUserToDomain(userMapper) }
    }

    override fun updateCommunitiesList(community: DomainCommunity) {
        /*_communitiesList.update {
            _communitiesList.value.firstOrNull { it.id == community.id }?.let {
                emit(it.mapCommunityToDomain(communityMapperToDomain))
            }
        }*/
        _communitiesList.update { currentList ->
            currentList.map { existingCommunity ->
                community.mapCommunityToData(communityMapperToData).let { newCommunity ->
                    if (existingCommunity.id == newCommunity.id) {
                        existingCommunity.copy(users = newCommunity.users)
                    } else {
                        existingCommunity
                    }
                }
            }
        }
    }
}