package com.turitsynanton.android.wbtech.data.di

import com.turitsynanton.android.wbtech.data.repository.AuthRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.CommunityRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.MeetingRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.mapper.communities.CommunityMapper
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import com.turitsynanton.android.wbtech.data.repository.mapper.meetings.MeetingMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CommunityMapper)
    singleOf(::MeetingMapper)

    singleOf(::CommunityRepositoryImpl) bind CommunityRepository::class
    singleOf(::MeetingRepositoryImpl) bind MeetingRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}