package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.AuthViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModel<AuthViewModel> {
        AuthViewModel(repository = get())
    }
    viewModel<MeetingsViewModel> {
        MeetingsViewModel(repository = get())
    }
    viewModel<CommunityViewModel> {
        CommunityViewModel(repository = get())
    }
}