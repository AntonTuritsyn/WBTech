package com.turitsynanton.android.wbtech.domain.usecases.myprofile

import com.turitsynanton.android.wbtech.domain.models.DomainProfile
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

class GetMyProfileUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetMyProfileUseCase {
    override fun execute(): Flow<DomainUser> = dataListsRepository.getProfileFlow()
}