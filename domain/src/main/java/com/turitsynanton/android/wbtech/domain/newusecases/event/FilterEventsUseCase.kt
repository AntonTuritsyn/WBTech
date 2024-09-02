package com.turitsynanton.android.wbtech.domain.newusecases.event

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilterEventsUseCase: IFilterEventsUseCase {
    override fun execute(query: String, eventList: List<DomainEvent>): Flow<List<DomainEvent>> {
        return if (query.isEmpty()) {
            flow { emit(eventList) }
        } else {
            flow {
                emit (eventList.filter { event ->
                    event.city.contains(query, ignoreCase = true) ||
                            event.name.contains(query, ignoreCase = true)
                })
            }
        }
    }
}