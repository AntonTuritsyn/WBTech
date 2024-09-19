package com.turitsynanton.android.wbtech.domain.usecases.subscribers

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetSubscribersListUseCase {
    fun execute(communityId: String): Flow<List<DomainUser>>
}