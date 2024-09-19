package com.turitsynanton.android.wbtech.models

data class UiProfile(
    val id: String,
    val name: String,
    val city: String,
    val description: String,
    val tags: List<UiTag>,
    val avatar: String
)
