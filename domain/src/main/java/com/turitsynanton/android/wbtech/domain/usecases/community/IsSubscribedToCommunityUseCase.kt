package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class IsSubscribedToCommunityUseCase(
    private val dataListsRepository: DataListsRepository
): IIsSubscribedToCommunityUseCase {
    override fun execute(communityId: String): Flow<Boolean> {
        val communityDetails = dataListsRepository.getCommunityDetailsFlow(communityId)
        val profileId = dataListsRepository.getMyProfileFlow().map { it.id }
        val button  = MutableStateFlow<Boolean>(false)
        communityDetails.mapLatest { community ->
            button.update {
                community.users.any { it.id == profileId.first() }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return button
    }
}