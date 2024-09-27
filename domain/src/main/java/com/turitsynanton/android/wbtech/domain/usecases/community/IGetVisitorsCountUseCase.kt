package com.turitsynanton.android.wbtech.domain.usecases.community

import kotlinx.coroutines.flow.Flow

interface IGetVisitorsCountUseCase {
    fun execute(eventId: String): Flow<Int>
}