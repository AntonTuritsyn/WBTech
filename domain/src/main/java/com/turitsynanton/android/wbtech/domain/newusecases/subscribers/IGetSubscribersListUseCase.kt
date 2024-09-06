package com.turitsynanton.android.wbtech.domain.newusecases.subscribers

import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetSubscribersListUseCase {
    fun execute(communityId: String): Flow<List<DomainUser>>
}