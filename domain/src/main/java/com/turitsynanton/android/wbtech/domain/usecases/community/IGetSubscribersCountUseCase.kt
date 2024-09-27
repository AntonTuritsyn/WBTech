package com.turitsynanton.android.wbtech.domain.usecases.community

import kotlinx.coroutines.flow.Flow

interface IGetSubscribersCountUseCase {
    fun execute(communityId: String): Flow<Int>
}