package com.turitsynanton.android.wbtech.domain.usecases.registration

import kotlinx.coroutines.flow.Flow

interface ISetRegistrationStepUseCase {
    fun execute(): Flow<Int>
}