package com.turitsynanton.android.wbtech.data.repository

import android.content.Context
import androidx.room.Room
import com.turitsynanton.android.wbtech.data.database.PhotoDatabase
import com.turitsynanton.android.wbtech.data.storage.models.DataPhoto
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "photo-database"

class PhotoRepository(
    context: Context
) {

    private val database: PhotoDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    fun getPhoto(id: UUID): Flow<DataPhoto> = database.photoDao().getPhoto(id)
    fun updatePhoto(photo: DataPhoto) = database.photoDao().updatePhoto(photo)
}