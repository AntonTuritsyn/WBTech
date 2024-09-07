package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
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
            eventsPrepared.update {
                if (query.isEmpty()) {
                    events
                } else {
                    events.filter { event ->
                        event.name.contains(query, ignoreCase = true)
                    }
                }
            }
        }

    }

    fun invoke(): Flow<List<DomainEvent>> = eventsPrepared
}
