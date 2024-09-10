package com.turitsynanton.android.wbtech.domain.newusecases.myprofile.toggles

import com.turitsynanton.android.wbtech.domain.newrepository.InfoVisibilityInProfileRepository

internal class SetCommunitiesVisibilityUseCase(
    private val communitiesListVisibilityInProfileRepository: InfoVisibilityInProfileRepository
): ISetListsVisibilityUseCase {
    override suspend fun execute(key: String, isVisible: Boolean) {
        communitiesListVisibilityInProfileRepository.setVisibilityInProfile(key, isVisible)
    }
}