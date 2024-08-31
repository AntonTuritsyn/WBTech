package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunityDetailsUseCase {
    fun execute(comunityId: String) : Flow<DomainCommunity?>
}