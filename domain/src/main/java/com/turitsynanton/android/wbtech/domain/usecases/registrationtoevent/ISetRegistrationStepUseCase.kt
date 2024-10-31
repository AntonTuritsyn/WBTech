package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent

import kotlinx.coroutines.flow.Flow

interface ISetRegistrationStepUseCase {
    fun execute(stepFromVm: Int): Flow<Int>

}