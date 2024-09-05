package com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen

import kotlinx.coroutines.flow.Flow

interface IInfoEventListScreenInteractor {
    fun invoke(): Flow<CombinedEventListScreenInfo>
}