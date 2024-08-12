package com.turitsynanton.android.wbtech.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.turitsynanton.android.wbtech.data.storage.models.DataPhoto
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PhotoDao {

    @Query("SELECT * FROM dataphoto WHERE id = (:id)")
    fun getPhoto(id: UUID): Flow<DataPhoto>

    @Update
    fun updatePhoto(photo: DataPhoto)
}