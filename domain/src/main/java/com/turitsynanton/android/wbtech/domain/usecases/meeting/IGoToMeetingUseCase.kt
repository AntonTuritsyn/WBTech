package com.turitsynanton.android.wbtech.domain.usecases.meeting

import kotlinx.coroutines.flow.Flow

interface IGoToMeetingUseCase {
    fun execute(): Flow<Boolean>
}