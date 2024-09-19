package com.turitsynanton.android.wbtech.data.storage.models

data class DataEvent(
    val id: String,
    val name: String,
    val date: String,
    val city: String,
    val description: String,
    val host: DataUser,
    val participants: List<DataUser>,
    val tags: List<DataTag>,
    val icon: String
)
