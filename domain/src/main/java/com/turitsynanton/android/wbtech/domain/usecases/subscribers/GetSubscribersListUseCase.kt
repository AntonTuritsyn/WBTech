package com.turitsynanton.android.wbtech.domain.usecases.subscribers

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetSubscribersListUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetSubscribersListUseCase {
    override fun execute(communityId: String): Flow<List<DomainUser>> {
        val communitiesList = dataListsRepository.getCommunitiesListFlow()
        val usersList = MutableStateFlow<List<DomainUser>>(emptyList())
        communitiesList.mapLatest { list ->
            usersList.update {
                list.first { it.id == communityId }.users
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return usersList
    }
}