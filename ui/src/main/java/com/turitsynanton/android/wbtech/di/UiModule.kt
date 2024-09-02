package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventMapper
import com.turitsynanton.android.wbtech.models.mapper.HostMapper
import com.turitsynanton.android.wbtech.models.mapper.OrganizerMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import com.turitsynanton.android.wbtech.models.mapper.TagMapper
import com.turitsynanton.android.wbtech.uinew.screens.communitydetails.ScreenCommunityDetailsViewModel
import com.turitsynanton.android.wbtech.uinew.screens.eventslist.ScreenEventsListViewModel
import com.turitsynanton.android.wbtech.uinew.screens.eventdetails.ScreenEventDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
//    viewModelOf(::AuthViewModel)


    viewModelOf(::ScreenEventsListViewModel)
    viewModelOf(::ScreenEventDetailsViewModel)

    viewModelOf(::ScreenCommunityDetailsViewModel)

    singleOf(::CommunityCardMapper)
    singleOf(::CommunityMapper)
    singleOf(::EventCardMapper)
    singleOf(::EventMapper)
    singleOf(::HostMapper)
    singleOf(::OrganizerMapper)
    singleOf(::PersonCardMapper)
    singleOf(::TagMapper)

//    viewModelOf(::ScreenProfileViewModel)
}