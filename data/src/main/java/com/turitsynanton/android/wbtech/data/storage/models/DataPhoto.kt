package com.turitsynanton.android.wbtech.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class DataPhoto(
    @PrimaryKey val id: UUID,
    val photoFileName: String
)
