package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: Repository) : ViewModel() {

/*    private val _userInfo: MutableStateFlow<User> = MutableStateFlow(
        com.turitsynanton.android.wbtech.data.storage.models.User()
    )
    fun getUserInfoFlow(): StateFlow<User> = _userInfo.asStateFlow()

    fun saveUser(newUser: com.turitsynanton.android.wbtech.data.storage.models.User) {
        viewModelScope.launch {
            repository.saveUser(
                user = com.turitsynanton.android.wbtech.data.storage.models.User().copy(
                    name = newUser.name,
                    surname = newUser.surname,
                    phone = newUser.phone
                )
            )
        }
    }*/
}