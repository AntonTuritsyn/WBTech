package com.turitsynanton.android.wbtech.domain.newusecases.myprofile.toggles

interface ISetListsVisibilityUseCase {
    suspend fun execute(key: String, isVisible: Boolean)
}