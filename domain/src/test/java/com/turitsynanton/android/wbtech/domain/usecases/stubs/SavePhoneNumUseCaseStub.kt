package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.auth.ISavePhoneNumUseCase

class SavePhoneNumUseCaseStub : ISavePhoneNumUseCase {
    var savedPhoneNum: String = ""

    override fun execute(phoneNum: String) {
        savedPhoneNum = phoneNum
    }
}