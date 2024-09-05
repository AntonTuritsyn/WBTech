package com.turitsynanton.android.wbtech.domain.newusecases.experiment

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import kotlinx.coroutines.flow.Flow

interface IGetEventDetailsUseCaseExperiment {
    fun execute(eventId: String) : Unit
}