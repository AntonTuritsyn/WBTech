package com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen

import kotlinx.coroutines.flow.Flow

interface IInfoEventListScreenInteractor {
    fun invoke(): Flow<CombinedEventListScreenInfo>
}