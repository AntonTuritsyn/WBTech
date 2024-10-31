package com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.name

import kotlinx.coroutines.flow.Flow

interface IGetCodeStatusBarUseCase {
    fun getCodeStatusBar(): Flow<String>
}