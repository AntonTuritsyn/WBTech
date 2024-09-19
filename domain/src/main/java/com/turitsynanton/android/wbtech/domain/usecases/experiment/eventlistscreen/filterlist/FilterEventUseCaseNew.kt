package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class FilterEventUseCaseNew(
    private val innerFilterEventUseCaseExperiment: InnerFilterEventUseCaseNew,
    private val dataListsRepository: IDataListsRepository
) : IFilterEventUseCaseNew {
    private val eventsPrepared = MutableStateFlow<List<DomainEvent>>(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(query: String) {
        val eventsList = dataListsRepository.getEventsListFlow()

        eventsList.mapLatest { events ->
                if (query.isEmpty()) {
                    println("query.isEmpty(): ${query.isEmpty()}")
                    eventsPrepared.update { events }

                } else {
                    println("query.isEmpty(): ${query.isEmpty()}")
                    eventsPrepared.update{
                        events.filter { event ->
                            event.name.contains(query, ignoreCase = true)
                        }
                    }
                }

        }

    }

    fun invoke(): Flow<List<DomainEvent>> = eventsPrepared
}
