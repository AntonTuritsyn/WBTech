package com.turitsynanton.android.wbtech.domain.usecases.auth

internal interface ISaveUserUseCase {
    fun execute(name: String, surname: String, phone: String)
}