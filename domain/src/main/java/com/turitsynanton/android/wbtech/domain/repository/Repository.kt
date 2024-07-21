package com.turitsynanton.android.wbtech.domain.repository


import com.turitsynanton.android.wbtech.domain.models.Community
import com.turitsynanton.android.wbtech.domain.models.User
import com.turitsynanton.android.wbtech.domain.models.UserMainInfo
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUser(): User
    fun saveUser(user: User)
    fun getUserMainInfo() : UserMainInfo

    fun getCommunityList() : Flow<List<Community>>
}