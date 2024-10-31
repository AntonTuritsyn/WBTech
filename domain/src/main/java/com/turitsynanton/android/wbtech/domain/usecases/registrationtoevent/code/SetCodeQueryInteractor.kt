package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code

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

    override fun observeCodeStatusBarFlow(wrongCode: String, sendToNum: String, phoneNum: String): Flow<String> {
        codeStatusBar.update {
            "$sendToNum $phoneNum"
        }
        return codeStatusBar
    }

    override fun observeErrorCodeStatusBarFlow(wrongCode: String): Flow<String> {
        if (codeQuery.value != "1234") {
            codeStatusBar.update {
                wrongCode
            }
            codeFieldError.update { true }
            buttonRegistrationStatus.update { false }
        }
        return codeStatusBar
    }

    override fun checkIfRightCodeFlow(): Flow<Boolean> {
        val isCodeRight = MutableStateFlow(false)
        if (codeQuery.value == "1234") {
            isCodeRight.update {
                true
            }
        } else {
            isCodeRight.update {
                false
            }
        }
        return isCodeRight
    }
}