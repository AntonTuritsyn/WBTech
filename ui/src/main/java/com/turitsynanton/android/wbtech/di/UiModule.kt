package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.ui.screens.viewmodels.AuthViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModel<AuthViewModel> {
        AuthViewModel(
            iGetUserUseCase = get(),
//            saveUserUseCase = get()
        )
    }
    viewModel<MeetingsViewModel> {
        MeetingsViewModel(iGetMeetingListUseCase = get())
    }
    viewModel<CommunityViewModel> {
        CommunityViewModel(iGetCommunityListUseCase = get())
    }
    viewModelOf(::CommunityDetailsViewModel)
    /*viewModel<CommunityDetailsViewModel> {
        CommunityDetailsViewModel(iGetCommunityDetailsUseCase = get())
    }*/
    viewModel<MeetingDetailsViewModel> {
        MeetingDetailsViewModel(meetingId = get(), iGetMeetingDetailsUseCase = get())
    }
}