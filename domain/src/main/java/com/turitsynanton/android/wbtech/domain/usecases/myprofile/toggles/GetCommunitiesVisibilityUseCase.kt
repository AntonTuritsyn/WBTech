package com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles

import com.turitsynanton.android.wbtech.domain.repository.InfoVisibilityInProfileRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunitiesVisibilityUseCase(
    private val communitiesListVisibilityInProfileRepository: InfoVisibilityInProfileRepository
): IGetListsVisibilityUseCase {
    override fun execute(key: String): Flow<Boolean> =
        communitiesListVisibilityInProfileRepository.getVisibilityInProfile(key)
}