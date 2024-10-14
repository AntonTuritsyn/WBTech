package com.turitsynanton.android.wbtech.domain.usecases.registration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SetRegistrationStepUseCase: ISetRegistrationStepUseCase {
    override fun execute(stepFromVm: Int): Flow<Int> {
        val stepToVm: MutableStateFlow<Int> = MutableStateFlow(1)
        stepToVm.update {
            stepFromVm + 1
        }
        println("stepToVm: ${stepToVm.value}")
        return stepToVm
    }
}