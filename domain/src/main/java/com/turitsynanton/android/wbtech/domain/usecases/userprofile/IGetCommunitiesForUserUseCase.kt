package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunitiesForUserUseCase {
    fun execute(userId: String): Flow<List<DomainCommunity>>
}