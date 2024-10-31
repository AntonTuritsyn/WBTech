package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.phone

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class SetPhoneQueryInteractor: ISetPhoneQueryInteractor {

    private val phoneToSave = MutableStateFlow("")

    override fun setPhoneQueryFlow(phone: String): Flow<String> {
        val phoneToQuery = MutableStateFlow("")
        phoneToQuery.update {
            phone.trim().take(10)
        }
        phoneToSave.update {
            phone.trim().take(10)
        }
        return phoneToQuery
    }

    override fun setButtonSendCodeStatusFlow(): Flow<Boolean> {
        val buttonSendCodeStatus = MutableStateFlow(false)
        if (phoneToSave.value.length > 9) {
            buttonSendCodeStatus.update {
                true
            }
        } else {
            buttonSendCodeStatus.update {
                false
            }
        }
        return buttonSendCodeStatus
    }
}