package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.data.repository.AuthRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.CommunityRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.MeetingRepositoryImpl
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository
import com.turitsynanton.android.wbtech.domain.repository.meeting.MeetingRepository
import org.koin.dsl.module

val dataModule = module {
    single<CommunityRepository> {
        CommunityRepositoryImpl()
    }
    single<MeetingRepository> {
        MeetingRepositoryImpl()
    }
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}