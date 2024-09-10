package com.turitsynanton.android.wbtech.data.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.turitsynanton.android.wbtech.data.dataStore
import com.turitsynanton.android.wbtech.data.newrepository.DataListsRepositoryImpl
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.CommunityMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.EventsListVisibilityInProfileRepositoryIml
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.UsersMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.TagsMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.HostMapper
import com.turitsynanton.android.wbtech.data.newrepository.newmapper.ProfileMapper
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.newrepository.InfoVisibilityInProfileRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.get
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val dataModule = module {

    singleOf(::CommunityMapper)
    singleOf(::EventMapper)
    singleOf(::UsersMapper)
    singleOf(::TagsMapper)
    singleOf(::HostMapper)
    singleOf(::ProfileMapper)

    /*singleOf(::CommunityRepositoryImpl) bind CommunityRepository::class
    singleOf(::EventRepositoryImpl) bind EventRepository::class*/

    singleOf(::DataListsRepositoryImpl) bind IDataListsRepository::class
    singleOf(::EventsListVisibilityInProfileRepositoryIml) bind InfoVisibilityInProfileRepository::class

    single<DataStore<Preferences>> {
        val context: Context = get()
        context.dataStore
    }
}