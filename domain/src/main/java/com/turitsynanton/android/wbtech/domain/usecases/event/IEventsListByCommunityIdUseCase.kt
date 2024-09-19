package com.turitsynanton.android.wbtech.domain.usecases.event

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IEventsListByCommunityIdUseCase {
    fun execute(communityId: String) : Flow<List<DomainEvent>>
}