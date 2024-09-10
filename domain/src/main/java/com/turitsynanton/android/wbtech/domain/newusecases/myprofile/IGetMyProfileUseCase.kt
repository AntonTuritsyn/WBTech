package com.turitsynanton.android.wbtech.domain.newusecases.myprofile

import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile
import kotlinx.coroutines.flow.Flow

interface IGetMyProfileUseCase {
    fun execute(): Flow<DomainProfile>
}