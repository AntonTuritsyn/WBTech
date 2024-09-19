package com.turitsynanton.android.wbtech.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.CommunityMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.mapCommunityToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class CommunityRepositoryImpl(private val mapper: CommunityMapperToDomain) : CommunityRepository {

    private val communityList = generateCommunitiesList()

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(communityList) }.map { it.mapCommunityToDomain(mapper) }

    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity?> =
        flow { emit(communityList.find { it.id == comunityId }?.mapCommunityToDomain(mapper)) }
}