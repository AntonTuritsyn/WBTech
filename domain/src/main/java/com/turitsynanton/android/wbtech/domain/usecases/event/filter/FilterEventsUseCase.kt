package com.turitsynanton.android.wbtech.domain.usecases.event.filter

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class FilterEventsUseCase(
    private val dataListsRepository: DataListsRepository
): IFilterEventsUseCase {
    override fun execute(query: String): Flow<List<DomainEvent>> {
        val events = dataListsRepository.getEventsListFlow()
        return if (query.isEmpty()) {
            events
        } else {
            events.mapLatest {
                it.filter { event ->
                    event.city.contains(query, ignoreCase = true) ||
                            event.name.contains(query, ignoreCase = true)
                }
            }
            /*flow {
                emit (eventList.filter { event ->
                    event.city.contains(query, ignoreCase = true) ||
                            event.name.contains(query, ignoreCase = true)
                })
            }*/
        }
    }
}