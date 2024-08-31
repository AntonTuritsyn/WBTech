package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.uinew.newviewmodel.ScreenProfileViewModel
import com.turitsynanton.android.wbtech.uinew.newviewmodel.ScreenCommunityDetailsViewModel
import com.turitsynanton.android.wbtech.uinew.newviewmodel.ScreenEventsListViewModel
import com.turitsynanton.android.wbtech.uinew.newviewmodel.ScreenEventDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
//    viewModelOf(::AuthViewModel)


    viewModelOf(::ScreenEventsListViewModel)
    viewModelOf(::ScreenEventDetailsViewModel)

    viewModelOf(::ScreenCommunityDetailsViewModel)

//    viewModelOf(::ScreenProfileViewModel)
}