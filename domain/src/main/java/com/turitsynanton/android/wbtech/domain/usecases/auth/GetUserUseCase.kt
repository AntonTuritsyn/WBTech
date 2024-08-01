package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository

internal class GetUserUseCase(private val authRepository: AuthRepository) : IGetUserUseCase {
    override fun execute(userId: Long) = authRepository.getUser(userId)
}