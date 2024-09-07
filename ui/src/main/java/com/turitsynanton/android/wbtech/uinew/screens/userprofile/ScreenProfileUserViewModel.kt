package com.turitsynanton.android.wbtech.uinew.screens.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newusecases.users.IGetUserFullInfoUseCase
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.models.mapper.PersonMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenProfileUserViewModel(
    userId: String,
    private val personMapper: PersonMapper,
    private val getUserFullInfoUseCase: IGetUserFullInfoUseCase
): ViewModel() {
    private val _userInfo: MutableStateFlow<UiPerson?> = MutableStateFlow(null)
    private val userInfo: StateFlow<UiPerson?> = _userInfo.asStateFlow()

    fun getUserInfoFlow(): StateFlow<UiPerson?> = userInfo

    init {
        getUserInfo(userId)
    }

    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            getUserFullInfoUseCase.execute(userId).collect { userInfo ->
                _userInfo.update { userInfo?.let { it1 -> personMapper.mapToUi(it1) } }
            }
        }
    }
}