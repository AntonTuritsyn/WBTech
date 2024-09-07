package com.turitsynanton.android.wbtech.data.newrepository

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.CommunityMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.mapCommunityToDomain
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@RequiresApi(Build.VERSION_CODES.O)
internal class CommunityRepositoryImpl(private val mapper: CommunityMapper) : CommunityRepository {

    private val communityList = generateCommunitiesList()

    override fun getCommunitiesListFlow(): Flow<List<DomainCommunity>> =
        flow { emit(communityList) }.map { it.mapCommunityToDomain(mapper) }

    override fun getCommunityDetailsFlow(comunityId: String): Flow<DomainCommunity?> =
        flow { emit(communityList.find { it.id == comunityId }?.mapCommunityToDomain(mapper)) }
}