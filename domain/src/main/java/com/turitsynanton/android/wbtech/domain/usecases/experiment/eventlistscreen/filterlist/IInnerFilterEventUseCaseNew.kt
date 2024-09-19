package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist

interface IInnerFilterEventUseCaseNew {
    fun execute(query: String/*, eventList: List<DomainEvent>*/): Unit
}