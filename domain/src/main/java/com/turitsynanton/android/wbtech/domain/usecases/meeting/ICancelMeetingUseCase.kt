package com.turitsynanton.android.wbtech.domain.usecases.meeting

import kotlinx.coroutines.flow.Flow

interface ICancelMeetingUseCase {
    fun execute(): Flow<Boolean>
}