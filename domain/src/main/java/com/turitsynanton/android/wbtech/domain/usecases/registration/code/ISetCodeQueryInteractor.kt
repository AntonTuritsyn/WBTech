package com.turitsynanton.android.wbtech.domain.usecases.registration.code

import kotlinx.coroutines.flow.Flow

interface ISetCodeQueryInteractor {
    fun observeCodeQueryFlow(query: String): Flow<String>
    fun observeButtonRegistrationStatusFlow(): Flow<Boolean>
    fun observeCodeFieldErrorFlow(): Flow<Boolean>

    fun observeCodeStatusBarFlow(): Flow<String>
    fun setStepIfRightCode(setStep: (Unit) -> Unit)
}