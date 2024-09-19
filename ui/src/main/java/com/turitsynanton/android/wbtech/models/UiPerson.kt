package com.turitsynanton.android.wbtech.models

data class UiPerson(
    val id: String,
    val name: String,
    val city: String,
    val phone: String,
    val description: String,
    val tags: List<UiTag>,
    val avatar: String
)
