package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.DataListsRepository
import com.turitsynanton.android.wbtech.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class FilterEventUseCaseNew(
    private val innerFilterEventUseCaseExperiment: InnerFilterEventUseCaseNew,
    private val eventRepository: EventRepository
) : IFilterEventUseCaseNew {
    private val eventsPrepared = MutableStateFlow<List<DomainEvent>>(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(query: String) {
        val eventsList = eventRepository.getEventsListFlow()

        eventsList.mapLatest { events ->
            if (query.isEmpty()) {
                println("query.isEmpty(): ${query.isEmpty()}")
                eventsPrepared.update { events }

            } else {
                println("query.isEmpty(): ${query.isEmpty()}")
                eventsPrepared.update {
                    events.filter { event ->
                        event.name.contains(query, ignoreCase = true)
                    }
                }
            }
        }
    }

    operator fun invoke(): Flow<List<DomainEvent>>  {
        println("eventsPrepared: ${eventsPrepared.value}")
        return eventsPrepared
    }
}
