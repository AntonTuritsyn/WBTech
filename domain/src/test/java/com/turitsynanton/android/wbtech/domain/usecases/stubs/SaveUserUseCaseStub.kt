package com.turitsynanton.android.wbtech.domain.usecases.stubs

import com.turitsynanton.android.wbtech.domain.usecases.auth.ISaveUserUseCase

class SaveUserUseCaseStub : ISaveUserUseCase {
    var savedName: String = ""
    var savedSurname: String = ""
    var savedPhone: String = ""

    override fun execute(name: String, surname: String, phone: String) {
        savedName = name
        savedSurname = surname
        savedPhone = phone
    }
}