package com.turitsynanton.android.wbtech.domain.repository.auth

import com.turitsynanton.android.wbtech.domain.models.DomainUser

interface AuthRepository {
    fun getUser(userId: Long): DomainUser

    fun saveUser(user: DomainUser)
}