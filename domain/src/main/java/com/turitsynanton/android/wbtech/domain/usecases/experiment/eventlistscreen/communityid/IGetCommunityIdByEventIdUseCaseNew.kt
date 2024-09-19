package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid

import kotlinx.coroutines.flow.Flow

interface IGetCommunityIdByEventIdUseCaseNew {
    fun execute(eventId: String)
    fun invoke(): Flow<String>
}
