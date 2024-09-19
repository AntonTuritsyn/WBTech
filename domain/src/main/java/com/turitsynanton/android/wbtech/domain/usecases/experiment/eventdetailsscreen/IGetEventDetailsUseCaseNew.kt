package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventdetailsscreen

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetEventDetailsUseCaseNew {
    fun execute(eventId: String)
    fun invoke(): Flow<DomainEvent?>
}