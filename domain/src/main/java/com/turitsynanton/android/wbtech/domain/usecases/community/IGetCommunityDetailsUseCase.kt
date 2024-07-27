package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunityDetailsUseCase {
    fun execute(comunityId: Long) : Flow<DomainCommunity?>
}