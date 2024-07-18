package com.turitsynanton.android.wbtech.data.storage

import com.turitsynanton.android.wbtech.data.storage.models.User

interface UserStorage {
    fun save(user: User)
    fun get() : User
}