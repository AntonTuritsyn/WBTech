package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.usecases.auth.GetUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.auth.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getUserUseCase: GetUserUseCase,
//    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _user: MutableStateFlow<DomainUser?> = MutableStateFlow(null)
    private val user: StateFlow<DomainUser?> = _user.asStateFlow()

    fun getUserFlow(): StateFlow<DomainUser?> = user

    fun getUser(userId: Long) {
        viewModelScope.launch {
            getUserUseCase.execute(userId).collect {
                _user.value = it
            }
//            _user.value = getUserUseCase.execute(userId = userId)
        }
    }
}