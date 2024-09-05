package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetEventDetailsUseCaseNew {
    fun execute(eventId: String)
    fun invoke(): Flow<DomainEvent?>
}