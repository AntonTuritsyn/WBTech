package com.turitsynanton.android.wbtech.domain.usecases.registration.code

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class SetCodeQueryInteractor: ISetCodeQueryInteractor {

    private val codeLength = 4

    private val codeQuery = MutableStateFlow("")
    private val buttonRegistrationStatus = MutableStateFlow(false)
    private val codeFieldError = MutableStateFlow(false)
    private val codeStatusBar = MutableStateFlow("")

    override fun observeCodeQueryFlow(query: String): Flow<String> {
        codeQuery.update { query }
        if (codeQuery.value.length >= codeLength) {
            buttonRegistrationStatus.update { true }
        } else {
            codeFieldError.update { false }
            buttonRegistrationStatus.update { false }
        }
        return codeQuery
    }

    override fun observeButtonRegistrationStatusFlow(): Flow<Boolean> {
        return buttonRegistrationStatus
    }

    override fun observeCodeFieldErrorFlow(): Flow<Boolean> {
        return codeFieldError
    }

    override fun observeCodeStatusBarFlow(): Flow<String> {
        if (codeQuery.value != "1234") {
            codeStatusBar.update {
                "Неправильный код"
            }
            codeFieldError.update { true }
            buttonRegistrationStatus.update { false }
        }
        return codeStatusBar
    }

    override fun setStepIfRightCode(setStep: (Unit) -> Unit) {
        if (codeQuery.value == "1234") {
            setStep.invoke(Unit)
        }
    }
}