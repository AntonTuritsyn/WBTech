package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository

class AuthRepositoryImpl : AuthRepository{
    override fun getUser(userId: Long): DomainUser {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: DomainUser) {
        TODO("Not yet implemented")
    }
}