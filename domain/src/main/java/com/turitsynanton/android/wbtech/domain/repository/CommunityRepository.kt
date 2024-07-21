package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getCommunitiesList() : Flow<List<DomainCommunity>>

    fun getCommunityDetails(comunityId: Long) : DomainCommunity?
}