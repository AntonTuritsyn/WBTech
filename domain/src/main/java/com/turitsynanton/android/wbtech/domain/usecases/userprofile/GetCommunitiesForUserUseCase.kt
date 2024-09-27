package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

internal class GetCommunitiesForUserUseCase(
    private val dataListsRepository: DataListsRepository
): IGetCommunitiesForUserUseCase {
    override fun execute(userId: String): Flow<List<DomainCommunity>> {
        val communities = dataListsRepository.getCommunitiesListFlow()
        val communitiesForUser = MutableStateFlow<List<DomainCommunity>>(emptyList())

        communities.map { communitiesList->
            val filteredCommunities = communitiesList.filter { communities->
                communities.users.any { users ->
                    users.id == userId
                }
            }
            println("filteredCommunities $filteredCommunities")
            communitiesForUser.update { filteredCommunities }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return communitiesForUser
    }
}