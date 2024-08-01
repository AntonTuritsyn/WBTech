package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.auth.AuthRepository

internal class SaveUserUseCase(private val authRepository: AuthRepository) : ISaveUserUseCase {
    override fun execute(name: String, surname: String, phone: String) = authRepository.saveUser(
        DomainUser(name = name, surname = surname, phone = phone)
    )
}