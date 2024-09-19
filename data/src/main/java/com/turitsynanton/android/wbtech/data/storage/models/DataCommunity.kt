package com.turitsynanton.android.wbtech.data.storage.models

data class DataCommunity(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val tags: List<DataTag>,
    val users: List<DataUser>,
    val events: List<DataEvent>
)
