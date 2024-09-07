package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import kotlinx.coroutines.flow.Flow

interface IGetCommunityIdByEventIdUseCaseNew {
    fun execute(eventId: String)
}
