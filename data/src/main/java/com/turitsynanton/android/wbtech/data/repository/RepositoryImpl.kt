package com.turitsynanton.android.wbtech.data.repository


import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
class RepositoryImpl(private val user: User) : Repository {

    private var _user = User()

    override fun getUser(): User = User()
    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUserMainInfo(): UserMainInfo {
        val user = _user
        return mapToDomain(user)
    }

    override fun getCommunityList(): Flow<List<Community>> {
        return flow<List<Community>> { emit(communities) }
    }

    private fun mapToDomain(user: User) : UserMainInfo {
        return UserMainInfo(fullName = "${user.name} ${user.surname}", phone = user.phone)
    }
}*/
