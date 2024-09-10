package com.turitsynanton.android.wbtech.domain.newusecases.userprofile

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class GetCommunitiesForUserUseCase(
    private val dataListsRepository: IDataListsRepository
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