package com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles

interface ISetListsVisibilityUseCase {
    suspend fun execute(key: String, isVisible: Boolean)
}