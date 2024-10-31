package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.phone

import kotlinx.coroutines.flow.Flow

interface ISetPhoneQueryInteractor {
    fun setPhoneQueryFlow(phone: String): Flow<String>
    fun setButtonSendCodeStatusFlow(): Flow<Boolean>
}