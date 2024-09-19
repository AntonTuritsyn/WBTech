package com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles

import com.turitsynanton.android.wbtech.domain.repository.InfoVisibilityInProfileRepository

class SetEventsVisibilityUseCase(
    private val eventsListVisibilityInProfileRepository: InfoVisibilityInProfileRepository
): ISetListsVisibilityUseCase {
    override suspend fun execute(key: String, isVisible: Boolean) {
        eventsListVisibilityInProfileRepository.setVisibilityInProfile(key, isVisible)
    }
}