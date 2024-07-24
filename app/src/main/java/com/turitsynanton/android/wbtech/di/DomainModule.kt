package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.domain.usecases.community.GetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.GetCommunityListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.GetMeetingDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.GetMeetingListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCommunityListUseCase> {
        GetCommunityListUseCase(communityRepository = get())
    }
    factory<GetCommunityDetailsUseCase> {
        GetCommunityDetailsUseCase(communityRepository = get())
    }

    factory<GetMeetingListUseCase> {
        GetMeetingListUseCase(meetingRepository = get())
    }
    factory<GetMeetingDetailsUseCase> {
        GetMeetingDetailsUseCase(meetingRepository = get())
    }
}