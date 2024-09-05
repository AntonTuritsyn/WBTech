package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.domain.newrepository.IDataListsRepository
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

internal class GetEventDetailsUseCaseNew(
    private val dataListsRepository: IDataListsRepository,
    private val getEventsListUseCaseNew: GetEventsListUseCaseNew
) : IGetEventDetailsUseCaseNew {
    private val eventDetails = MutableStateFlow<DomainEvent?>(null)
    override fun execute(eventId: String) {
        getEventsListUseCaseNew().mapLatest { events ->
            events.forEach { event ->
                if (event.id == eventId)
                    eventDetails.update {
                        event
                    }
            }
        }
    }
    override fun invoke(): Flow<DomainEvent?> = eventDetails
}