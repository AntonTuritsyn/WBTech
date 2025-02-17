package com.turitsynanton.android.wbtech.di

import android.app.Activity
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.CommunityMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventMapper
import com.turitsynanton.android.wbtech.models.mapper.HostMapper
import com.turitsynanton.android.wbtech.models.mapper.OrganizerMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonMapper
import com.turitsynanton.android.wbtech.models.mapper.ProfileMapper
import com.turitsynanton.android.wbtech.models.mapper.TagMapper
import com.turitsynanton.android.wbtech.permissions.AndroidPermissionChecker
import com.turitsynanton.android.wbtech.permissions.LocationViewModel
import com.turitsynanton.android.wbtech.ui.screens.communitydetails.ScreenCommunityDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.eventslist.ScreenEventsListViewModel
import com.turitsynanton.android.wbtech.ui.screens.eventdetails.ScreenEventDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.myprofile.ScreenProfileMyViewModel
import com.turitsynanton.android.wbtech.ui.screens.participants.ScreenParticipantsViewModel
import com.turitsynanton.android.wbtech.ui.screens.registration.ScreenRegistrationForEventViewModel
import com.turitsynanton.android.wbtech.ui.screens.registrationend.ScreenFinishRegistrationViewModel
import com.turitsynanton.android.wbtech.ui.screens.subscribers.ScreenSubscribersViewModel
import com.turitsynanton.android.wbtech.ui.screens.userprofile.ScreenProfileUserViewModel
import com.turitsynanton.android.wbtech.ui.utils.ResourceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val uiModule = module {

    viewModelOf(::ScreenEventsListViewModel)
    viewModelOf(::ScreenEventDetailsViewModel)

    viewModelOf(::ScreenCommunityDetailsViewModel)
    viewModelOf(::ScreenParticipantsViewModel)
    viewModelOf(::ScreenSubscribersViewModel)
    viewModelOf(::ScreenProfileUserViewModel)
    viewModelOf(::ScreenProfileMyViewModel)
    viewModelOf(::ScreenRegistrationForEventViewModel)
    viewModelOf(::ScreenFinishRegistrationViewModel)

    singleOf(::CommunityCardMapper)
    singleOf(::CommunityMapper)
    singleOf(::EventCardMapper)
    singleOf(::EventMapper)
    singleOf(::HostMapper)
    singleOf(::OrganizerMapper)
    singleOf(::PersonCardMapper)
    singleOf(::TagMapper)
    singleOf(::PersonMapper)
    singleOf(::ProfileMapper)
    singleOf(::ResourceProvider)

    // Provide AndroidPermissionChecker
    factory { (activity: Activity) -> AndroidPermissionChecker(context = get(), activity = activity) }

    // Provide LocationViewModel
    viewModel { (activity: Activity) ->
        LocationViewModel(locationPermissionChecker = get { parametersOf(activity) })
    }

//    viewModelOf(::ScreenProfileViewModel)
}