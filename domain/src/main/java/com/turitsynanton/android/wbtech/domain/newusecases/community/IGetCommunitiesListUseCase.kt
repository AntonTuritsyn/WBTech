package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunitiesListUseCase {
    fun execute(): Flow<List<DomainCommunity>>
}