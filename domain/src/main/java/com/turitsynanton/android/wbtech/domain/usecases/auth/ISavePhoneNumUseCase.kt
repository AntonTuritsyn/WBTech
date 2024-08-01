package com.turitsynanton.android.wbtech.domain.usecases.auth

internal interface ISavePhoneNumUseCase {
    fun execute(phoneNum: String)
}