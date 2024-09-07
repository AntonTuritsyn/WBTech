package com.turitsynanton.android.wbtech.domain.newusecases.participants

import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetParticipantsListUseCase(
    private val dataListsRepository: IDataListsRepository
): IGetParticipantsListUseCase {
    override fun execute(eventId: String): Flow<List<DomainUser>> {
        val eventsList = dataListsRepository.getEventsListFlow()
        val usersList = MutableStateFlow<List<DomainUser>>(emptyList())
        eventsList.mapLatest { list ->
            usersList.update {
                list.first { it.id == eventId }.participants
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return usersList
    }
}