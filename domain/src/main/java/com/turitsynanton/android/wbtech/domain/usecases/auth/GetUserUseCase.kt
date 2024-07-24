package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository
import com.turitsynanton.android.wbtech.domain.repository.community.CommunityRepository

class GetUserUseCase(private val authRepository: AuthRepository) {
    fun execute(userId: Long) = authRepository.getUser(userId)
}