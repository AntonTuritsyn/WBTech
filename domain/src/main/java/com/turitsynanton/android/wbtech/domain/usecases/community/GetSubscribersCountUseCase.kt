package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetSubscribersCountUseCase(
    private val dataListsRepository: DataListsRepository
): IGetSubscribersCountUseCase {
    override fun execute(communityId: String): Flow<Int> {
        val subscribersToShow = 5
        val subscribersCount: MutableStateFlow<Int> = MutableStateFlow(0)
        val communityDetails = dataListsRepository.getCommunityDetailsFlow(communityId)
        communityDetails.mapLatest { community ->
            if (community.users.size > subscribersToShow) {
                subscribersCount.update { community.users.size - subscribersToShow }
            } else {
                subscribersCount.update { 0 }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return subscribersCount
    }
}