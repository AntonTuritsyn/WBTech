package com.turitsynanton.android.wbtech.domain.repository


import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.domain.models.UserMainInfo
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUser(): DomainUser
    fun saveUser(user: DomainUser)
    fun getUserMainInfo() : UserMainInfo

    fun getCommunityList() : Flow<List<DomainCommunity>>
}