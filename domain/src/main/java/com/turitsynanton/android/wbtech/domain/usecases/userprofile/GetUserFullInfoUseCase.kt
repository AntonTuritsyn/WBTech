package com.turitsynanton.android.wbtech.domain.usecases.userprofile

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetUserFullInfoUseCase(
    private val dataListsRepository: DataListsRepository
): IGetUserFullInfoUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(userId: String): Flow<DomainUser?> {
        val userListFlow = dataListsRepository.getUsersListFlow()
        val user = MutableStateFlow<DomainUser?>(null)
        userListFlow.mapLatest { list->
            user.update {
                list.first { it.id == userId }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return user
    }
}