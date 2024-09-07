package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import javax.management.Query

interface IFilterEventUseCaseNew {
    fun execute(query: String)
}