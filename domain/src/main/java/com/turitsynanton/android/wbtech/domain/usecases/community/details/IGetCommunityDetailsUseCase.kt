package com.turitsynanton.android.wbtech.domain.usecases.community.details

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunityDetailsUseCase {
    fun execute(communityId: String) : Flow<DomainCommunity>
}