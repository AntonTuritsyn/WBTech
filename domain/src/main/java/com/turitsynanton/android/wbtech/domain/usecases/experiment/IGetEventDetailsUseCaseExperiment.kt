package com.turitsynanton.android.wbtech.domain.usecases.experiment

interface IGetEventDetailsUseCaseExperiment {
    fun execute(eventId: String) : Unit
}