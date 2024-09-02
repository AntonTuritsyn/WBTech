package com.turitsynanton.android.wbtech.models

data class UiEvent(
    val id: String,
    val title: String,
    val date: String,
    val address: String,
    val tags: List<UiTag>,
    val description: String,
//    val events: List<UiEventCard>
)
