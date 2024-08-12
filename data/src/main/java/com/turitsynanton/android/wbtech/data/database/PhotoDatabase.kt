package com.turitsynanton.android.wbtech.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.turitsynanton.android.wbtech.data.storage.models.DataPhoto

@Database(entities = [DataPhoto::class], version = 1, exportSchema = true)
abstract class PhotoDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}