package com.turitsynanton.android.wbtech.domain.newusecases.myprofile

import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.flow.Flow

class GetMyProfileUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetMyProfileUseCase {
    override fun execute(): Flow<DomainProfile> = dataListsRepository.getProfileFlow()
}