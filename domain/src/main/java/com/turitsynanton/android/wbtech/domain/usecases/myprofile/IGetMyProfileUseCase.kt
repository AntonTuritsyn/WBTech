package com.turitsynanton.android.wbtech.domain.usecases.myprofile

import com.turitsynanton.android.wbtech.domain.models.DomainProfile
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface IGetMyProfileUseCase {
    fun execute(): Flow<DomainUser>
}