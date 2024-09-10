package com.turitsynanton.android.wbtech.domain.newusecases

import com.turitsynanton.android.wbtech.domain.newusecases.experiment.CombinedEventInfo
import kotlinx.coroutines.flow.Flow

interface IInteractorFullInfoExperiment {
    fun invoke(): Flow<CombinedEventInfo>
}