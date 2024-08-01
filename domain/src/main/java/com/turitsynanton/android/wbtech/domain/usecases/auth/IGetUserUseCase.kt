package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetUserUseCase {
    fun execute(userId: Long): Flow<DomainUser>
}