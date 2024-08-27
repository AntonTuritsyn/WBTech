package com.turitsynanton.android.wbtech.data.storage.newmodels

data class DataUser(
    val id: String,
    val name: String,
    val city: String,
    val description: String,
    val tags: List<DataTag>,
    val icon: String
)