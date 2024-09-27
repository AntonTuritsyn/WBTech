package com.turitsynanton.android.wbtech.domain.usecases.myprofile

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow

internal class GetMyProfileUseCase(
    private val dataListsRepository: DataListsRepository
): IGetMyProfileUseCase {
    override fun execute(): Flow<DomainUser> = dataListsRepository.getProfileFlow()
}