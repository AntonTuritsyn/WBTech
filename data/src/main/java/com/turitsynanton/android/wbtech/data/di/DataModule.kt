package com.turitsynanton.android.wbtech.data.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.turitsynanton.android.wbtech.data.newrepository.DataListsRepositoryImpl
import com.turitsynanton.android.wbtech.data.newrepository.CommunityRepositoryImpl
import com.turitsynanton.android.wbtech.data.newrepository.EventRepositoryImpl
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.CommunityMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.UsersMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.TagsMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.HostMapper
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.newrepository.CommunityRepository
import com.turitsynanton.android.wbtech.domain.newrepository.EventRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val dataModule = module {

    singleOf(::CommunityMapper)
    singleOf(::EventMapper)
    singleOf(::UsersMapper)
    singleOf(::TagsMapper)
    singleOf(::HostMapper)

    /*singleOf(::CommunityRepositoryImpl) bind CommunityRepository::class
    singleOf(::EventRepositoryImpl) bind EventRepository::class*/

    singleOf(::DataListsRepositoryImpl) bind DataListsRepository::class
}