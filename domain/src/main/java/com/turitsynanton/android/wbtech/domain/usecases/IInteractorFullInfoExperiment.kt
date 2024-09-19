package com.turitsynanton.android.wbtech.domain.usecases

import com.turitsynanton.android.wbtech.domain.usecases.experiment.CombinedEventInfo
import kotlinx.coroutines.flow.Flow

interface IInteractorFullInfoExperiment {
    fun invoke(): Flow<CombinedEventInfo>
}