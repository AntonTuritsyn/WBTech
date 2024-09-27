package com.turitsynanton.android.wbtech.domain.usecases.registration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class SetRegistrationStepUseCase: ISetRegistrationStepUseCase {
    override fun execute(): Flow<Int> {
        val stepToVm: MutableStateFlow<Int> = MutableStateFlow(0)
        stepToVm.update { step ->
            step + 1
        }
        return flowOf(0)
    }
}