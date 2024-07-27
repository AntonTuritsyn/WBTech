package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.auth.ISendCodeUseCase

class SendCodeUseCaseStub : ISendCodeUseCase {
    var codeForTest: String = ""
    var returnValue: Boolean = true

    override fun execute(code: String): Boolean {
        codeForTest = code
        return returnValue
    }
}