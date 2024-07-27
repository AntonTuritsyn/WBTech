package com.turitsynanton.android.wbtech.domain.usecases.auth

internal interface ISendCodeUseCase {
    fun execute(code: String): Boolean
}