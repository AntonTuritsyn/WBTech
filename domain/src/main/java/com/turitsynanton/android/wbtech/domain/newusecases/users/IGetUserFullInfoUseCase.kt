package com.turitsynanton.android.wbtech.domain.newusecases.users

import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetUserFullInfoUseCase {
    fun execute(userId: String): Flow<DomainUser?>
}