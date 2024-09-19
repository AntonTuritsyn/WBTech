package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetEventsForUserUseCase {
    fun execute(userId: String): Flow<List<DomainEvent>>
}