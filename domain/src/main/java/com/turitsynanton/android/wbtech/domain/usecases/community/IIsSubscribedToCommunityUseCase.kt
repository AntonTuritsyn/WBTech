package com.turitsynanton.android.wbtech.domain.usecases.community

import kotlinx.coroutines.flow.Flow

interface IIsSubscribedToCommunityUseCase {
    fun execute(communityId: String): Flow<Boolean>
}