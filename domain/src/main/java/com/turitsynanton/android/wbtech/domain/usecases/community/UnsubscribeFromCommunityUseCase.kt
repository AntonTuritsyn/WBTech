package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class UnsubscribeFromCommunityUseCase(
    private val dataListsRepository: DataListsRepository
) : IUnsubscribeFromCommunityUseCase {
    private val myProfile = dataListsRepository.getMyProfileFlow()
    override fun execute(communityId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataListsRepository.getCommunityDetailsFlow(communityId)
                .collect { existingCommunity ->
                    myProfile.collect { me ->
                        if (existingCommunity.users.contains(me)) {
                            val newCommunity = existingCommunity.copy(
                                users = existingCommunity.users.filter { user -> user.id != me.id }
                            )
                            println("newCommunity: $newCommunity")
                            dataListsRepository.updateCommunitiesList(newCommunity)
                        }
                    }
                }
        }
    }
}