package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.data.repository.CommunityRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.MeetingRepositoryImpl
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import com.turitsynanton.android.wbtech.domain.repository.MeetingRepository
import com.turitsynanton.android.wbtech.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    single<CommunityRepository> {
        CommunityRepositoryImpl()
    }
    single<MeetingRepository> {
        MeetingRepositoryImpl()
    }
}