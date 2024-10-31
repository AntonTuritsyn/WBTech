package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code

import kotlinx.coroutines.flow.Flow

interface ISetCodeQueryInteractor {
    fun observeCodeQueryFlow(query: String): Flow<String>
    fun observeButtonRegistrationStatusFlow(): Flow<Boolean>
    fun observeCodeFieldErrorFlow(): Flow<Boolean>

    fun observeCodeStatusBarFlow(wrongCode: String, sendToNum: String, phoneNum: String): Flow<String>
    fun observeErrorCodeStatusBarFlow(wrongCode: String): Flow<String>
    fun checkIfRightCodeFlow(): Flow<Boolean>
}