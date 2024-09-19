package com.turitsynanton.android.wbtech.domain.usecases.experiment

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.domain.repository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.usecases.IInteractorFullInfoExperiment
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
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


class UseCaseInnerGetEventDetails() : IGetEventDetailsUseCaseExperiment {
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
//__________________________________
internal class InteractorFullInfoExperiment(
    private val getEventDetails: GetEventDetailsUseCaseExperiment,
    private val getEventList: GetEventListUseCaseExperiment,
    private val filterEventUseCaseExperiment: FilterEventUseCaseNew,
    private val getCommunityIdByEventIdUseCaseNew: IGetCommunityIdByEventIdUseCaseNew
) : IInteractorFullInfoExperiment {

    val innerFlow = combine(
        getEventDetails.invoke(),
        getEventList.execute(),
        filterEventUseCaseExperiment.invoke(),
        getCommunityIdByEventIdUseCaseNew.invoke()
    ) { eventDetails, eventList, filteredEvents, t1 ->
        CombinedEventInfo(eventDetails, eventList, filteredEvents, t1)
    }

    override fun invoke(): Flow<CombinedEventInfo> = innerFlow
}

data class CombinedEventInfo(
    val eventDetails: DomainEvent?,
    val eventList: List<DomainEvent>,
    val filteredEvents: List<DomainEvent?>,
    val id: String
)