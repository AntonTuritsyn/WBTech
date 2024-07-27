package com.turitsynanton.android.wbtech.domain.repository.auth

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getUser(userId: Long): Flow<DomainUser>

    fun saveUser(user: DomainUser)

    fun savePhoneNum(phoneNum: String)

    fun sendCode(code: String)
}