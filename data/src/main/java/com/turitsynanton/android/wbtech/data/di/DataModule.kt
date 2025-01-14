package com.turitsynanton.android.wbtech.data.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.turitsynanton.android.wbtech.data.dataStore
import com.turitsynanton.android.wbtech.data.repository.DataListsRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.EventRepositoryImpl
import com.turitsynanton.android.wbtech.data.repository.mappertodata.CommunityMapperToData
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.CommunityMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.EventMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.EventsListVisibilityInProfileRepositoryIml
import com.turitsynanton.android.wbtech.data.repository.mappertodata.EventMapperToData
import com.turitsynanton.android.wbtech.data.repository.mappertodata.TagsMapperToData
import com.turitsynanton.android.wbtech.data.repository.mappertodata.UsersMapperToData
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.UsersMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.TagsMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.HostMapperToDomain
import com.turitsynanton.android.wbtech.data.repository.mappertodomain.ProfileMapperToDomain
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import com.turitsynanton.android.wbtech.domain.repository.InfoVisibilityInProfileRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val dataModule = module {

    singleOf(::CommunityMapperToDomain)
    singleOf(::EventMapperToDomain)
    singleOf(::UsersMapperToDomain)
    singleOf(::TagsMapperToDomain)
    singleOf(::HostMapperToDomain)
    singleOf(::ProfileMapperToDomain)

    singleOf(::CommunityMapperToData)
    singleOf(::TagsMapperToData)
    singleOf(::UsersMapperToData)
    singleOf(::EventMapperToData)

    /*singleOf(::CommunityRepositoryImpl) bind CommunityRepository::class
    singleOf(::EventRepositoryImpl) bind EventRepository::class*/

    singleOf(::DataListsRepositoryImpl) bind DataListsRepository::class
    singleOf(::EventRepositoryImpl) bind EventRepository::class
    singleOf(::EventsListVisibilityInProfileRepositoryIml) bind InfoVisibilityInProfileRepository::class

    single<DataStore<Preferences>> {
        val context: Context = get()
        context.dataStore
    }
}