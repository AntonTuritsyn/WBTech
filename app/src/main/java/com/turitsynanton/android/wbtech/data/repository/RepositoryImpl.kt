package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.storage.UserStorage
import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.data.storage.models.Meeting
import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.domain.models.UserMainInfo
import com.turitsynanton.android.wbtech.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(/*private val userStorage: UserStorage*/) : Repository {

    private var _user = User()

    override fun getUser(): User = User()

    override fun saveUser(user: User) {
        this._user = user
    }

    override fun getUserMainInfo(): UserMainInfo {
        val user = _user
        return mapToDomain(user)
    }

    override fun getMeetingList(): Flow<List<Meeting>> {
        return flow<List<Meeting>> { emit(meetings) }
    }

    override fun getCommunityList(): Flow<List<Community>> {
        return flow<List<Community>> { emit(communities) }
    }

    private fun mapToDomain(user: User) : UserMainInfo {
        return UserMainInfo(fullName = "${user.name} ${user.surname}", phone = user.phone)
    }
}