package com.turitsynanton.android.wbtech.domain.newusecases.userprofile

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetCommunitiesForUserUseCase {
    fun execute(userId: String): Flow<List<DomainCommunity>>
}