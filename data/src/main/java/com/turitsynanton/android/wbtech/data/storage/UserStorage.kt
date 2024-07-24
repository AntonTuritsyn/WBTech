package com.turitsynanton.android.wbtech.data.storage

import com.turitsynanton.android.wbtech.data.storage.models.DataUser

interface UserStorage {
    fun save(user: DataUser)
    fun get() : DataUser
}