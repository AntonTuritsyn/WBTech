package com.turitsynanton.android.wbtech.domain.usecases.participants

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetParticipantsListUseCase {
    fun execute(eventId: String): Flow<List<DomainUser>>
}