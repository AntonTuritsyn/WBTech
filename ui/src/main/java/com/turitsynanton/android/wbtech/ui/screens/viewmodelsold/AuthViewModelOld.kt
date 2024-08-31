package com.turitsynanton.android.wbtech.ui.screens.viewmodelsold

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.usecases.auth.IGetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class AuthViewModelOld(
    private val iGetUserUseCase: IGetUserUseCase,
) : ViewModel() {

    private val _user: MutableStateFlow<DomainUser?> = MutableStateFlow(null)
    private val user: StateFlow<DomainUser?> = _user.asStateFlow()

    fun getUserFlow(): StateFlow<DomainUser?> = user

    fun getUser(userId: Long) {
        viewModelScope.launch {
            iGetUserUseCase.execute(userId).collect {
                _user.value = it
            }
//            _user.value = getUserUseCase.execute(userId = userId)
        }
    }
}