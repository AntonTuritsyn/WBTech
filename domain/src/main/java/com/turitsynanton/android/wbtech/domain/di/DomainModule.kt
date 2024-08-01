package com.turitsynanton.android.wbtech.domain.di

import com.turitsynanton.android.wbtech.domain.usecases.auth.GetUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.GetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.GetCommunityListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.GetMeetingDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.GetMeetingListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingsListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCommunityListUseCase) bind IGetCommunityListUseCase::class
    factoryOf(::GetCommunityDetailsUseCase) bind IGetCommunityDetailsUseCase::class

    factoryOf(::GetMeetingListUseCase) bind IGetMeetingsListUseCase::class
    factoryOf(::GetMeetingDetailsUseCase) bind IGetMeetingDetailsUseCase::class

    factoryOf(::GetUserUseCase)
}