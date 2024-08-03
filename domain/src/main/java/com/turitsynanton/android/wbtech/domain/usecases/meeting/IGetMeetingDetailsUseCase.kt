package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import kotlinx.coroutines.flow.Flow

interface IGetMeetingDetailsUseCase {
    fun execute(meetingId: String) : Flow<DomainMeeting>
}