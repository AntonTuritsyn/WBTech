package com.turitsynanton.android.wbtech.domain.newusecases.participants

import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetParticipantsListUseCase {
    fun execute(eventId: String): Flow<List<DomainUser>>
}