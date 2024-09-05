package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist

interface IInnerFilterEventUseCaseNew {
    fun execute(query: String/*, eventList: List<DomainEvent>*/): Unit
}