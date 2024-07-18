package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.data.storage.models.Meeting
import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.domain.models.UserMainInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    fun getUser(): User
    fun saveUser(user: User)
    fun getUserMainInfo() : UserMainInfo

    fun getMeetingList() : Flow<List<Meeting>>

    fun getCommunityList() : Flow<List<Community>>
}