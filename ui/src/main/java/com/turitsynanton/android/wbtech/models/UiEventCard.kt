package com.turitsynanton.android.wbtech.models

data class UiEventCard(
    val id: String,
    val name: String,
    val date: String,
    val address: String,
    val tags: List<UiTag>,
    val image: String
)
