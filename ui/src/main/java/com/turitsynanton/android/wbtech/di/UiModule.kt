package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.ui.screens.viewmodels.AuthViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::AuthViewModel)

    viewModelOf(::MeetingsViewModel)
    viewModelOf(::MeetingDetailsViewModel)

    viewModelOf(::CommunityViewModel)
    viewModelOf(::CommunityDetailsViewModel)
}