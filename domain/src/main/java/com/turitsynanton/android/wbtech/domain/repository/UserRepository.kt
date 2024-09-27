package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersListFlow() : Flow<List<DomainUser>>

    fun getUserDetailsFlow(userId: String) : Flow<DomainUser>
}