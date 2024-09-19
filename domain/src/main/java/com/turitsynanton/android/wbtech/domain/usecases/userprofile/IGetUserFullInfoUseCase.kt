package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetUserFullInfoUseCase {
    fun execute(userId: String): Flow<DomainUser?>
}