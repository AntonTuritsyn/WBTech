package com.turitsynanton.android.wbtech.domain.usecases

import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetVisitorsCountUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetVisitorsCountUseCase(
    private val dataListsRepository: DataListsRepository
): IGetVisitorsCountUseCase {
    override fun execute(eventId: String): Flow<Int> {
        val visitorsToShow = 5
        val visitorsCount: MutableStateFlow<Int> = MutableStateFlow(0)
        val eventDetails = dataListsRepository.getEventDetailsFlow(eventId)
        eventDetails.mapLatest { event ->
            if (event.participants.size > visitorsToShow) {
                visitorsCount.update { event.participants.size - visitorsToShow }
            } else {
                visitorsCount.update { 0 }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
        return visitorsCount
    }
}