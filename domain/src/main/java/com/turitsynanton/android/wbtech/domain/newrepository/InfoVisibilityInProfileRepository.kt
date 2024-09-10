package com.turitsynanton.android.wbtech.domain.newrepository

import kotlinx.coroutines.flow.Flow

interface InfoVisibilityInProfileRepository {
    suspend fun setVisibilityInProfile(key: String, isVisible: Boolean)
    fun getVisibilityInProfile(key: String): Flow<Boolean>
}