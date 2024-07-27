package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository{
    override fun getUser(userId: Long): Flow<DomainUser> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: DomainUser) {
        TODO("Not yet implemented")
    }

    override fun savePhoneNum(phoneNum: String) {
        TODO("Not yet implemented")
    }

    override fun sendCode(code: String) {
        TODO("Not yet implemented")
    }
}