package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository

class GetUserUseCase(private val authRepository: AuthRepository) {
    fun execute(userId: Long) = authRepository.getUser(userId)
}