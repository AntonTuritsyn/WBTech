package com.turitsynanton.android.wbtech.domain.newusecases.experiment

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.newusecases.event.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class GetEventListUseCaseExperiment(
    private val dataListsRepository: IDataListsRepository
) : IGetEventListUseCase {
    override fun execute(): Flow<List<DomainEvent>> = dataListsRepository.getEventsListFlow()
}

class GetEventDetailsUseCaseExperiment(
    private val useCaseInnerGetEventDetails: UseCaseInnerGetEventDetails,
    private val dataListsRepository: IDataListsRepository
) {
    private val eventsPrepared = MutableStateFlow<DomainEvent?>(null)

    fun execute() {
        useCaseInnerGetEventDetails.observe().mapLatest { it ->
            dataListsRepository.getEventDetailsFlowExperiment(it)
        }
    }

    fun invoke(): Flow<DomainEvent?> = eventsPrepared
}


class UseCaseInnerGetEventDetails(): IGetEventDetailsUseCaseExperiment {
    private val streamEventById = MutableStateFlow<String>("")

    private var lastId: String? = null

    override fun execute(eventId: String) {
        lastId = eventId
        streamEventById.update {
            eventId
        }
    }
    fun observe(): Flow<String> = streamEventById

}

/*class FilterEventsUseCase: IFilterEventsUseCase {
    private val queryForSearch = MutableStateFlow<String>("")

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

    fun observe(): Flow<String> = queryForSearch
}*/

internal class InteractorFullInfoExperiment(
    private val getEventDetails: GetEventDetailsUseCaseExperiment,
    private val getEventList: GetEventListUseCaseExperiment,
    private val filterEventUseCaseExperiment: FilterEventUseCaseNew
) {

    val innerFlow = combine(
        getEventDetails.invoke(),
        getEventList.execute(),
        filterEventUseCaseExperiment.invoke()
    ) { eventDetails, eventList, filteredEvents->
        CombinedEventInfo(eventDetails, eventList, filteredEvents)
    }
    fun invoke(): Flow<CombinedEventInfo> = innerFlow
}

data class CombinedEventInfo(
    val eventDetails: DomainEvent?,
    val eventList: List<DomainEvent>,
    val filteredEvents: List<DomainEvent?>
)