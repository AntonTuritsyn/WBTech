package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.Community
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getCommunitiesList() : Flow<List<Community>>

    fun getCommunityDetails(comunityId: Long) : Community?
}