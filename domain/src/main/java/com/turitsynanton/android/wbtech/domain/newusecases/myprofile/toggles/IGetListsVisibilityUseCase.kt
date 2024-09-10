package com.turitsynanton.android.wbtech.domain.newusecases.myprofile.toggles

import kotlinx.coroutines.flow.Flow

interface IGetListsVisibilityUseCase {
    fun execute(key: String): Flow<Boolean>
}