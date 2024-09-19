package com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles

import com.turitsynanton.android.wbtech.domain.repository.InfoVisibilityInProfileRepository
import kotlinx.coroutines.flow.Flow

class GetEventsVisibilityUseCase(
    private val eventsListVisibilityInProfileRepository: InfoVisibilityInProfileRepository
) : IGetListsVisibilityUseCase {
    override fun execute(key: String): Flow<Boolean> =
        eventsListVisibilityInProfileRepository.getVisibilityInProfile(key)
}