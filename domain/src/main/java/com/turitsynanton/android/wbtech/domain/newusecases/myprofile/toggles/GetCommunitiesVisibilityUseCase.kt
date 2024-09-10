package com.turitsynanton.android.wbtech.domain.newusecases.myprofile.toggles

import com.turitsynanton.android.wbtech.domain.newrepository.InfoVisibilityInProfileRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesVisibilityUseCase(
    private val communitiesListVisibilityInProfileRepository: InfoVisibilityInProfileRepository
): IGetListsVisibilityUseCase {
    override fun execute(key: String): Flow<Boolean> =
        communitiesListVisibilityInProfileRepository.getVisibilityInProfile(key)
}