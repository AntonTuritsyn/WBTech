package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    override fun execute(query: String, eventList: List<DomainEvent>) {
//        innerFilterEventUseCaseExperiment.observe().mapLatest { query ->
        eventsPrepared.update {
            if (query.isEmpty()) {
                eventList
                /*eventsPrepared.update {
                    eventList
                }*/
            } else {
                /*eventsPrepared.update {
                    eventList.filter { event ->
                        event.name.contains(query, ignoreCase = true)
                    }
                }*/
                eventList.filter { event ->
                    event.name.contains(query, ignoreCase = true)
                }
            }
        }
    }

    fun invoke(): Flow<List<DomainEvent>> = eventsPrepared
}
