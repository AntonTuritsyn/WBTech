package com.turitsynanton.android.wbtech.models

data class UiCommunity(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val tags: List<UiTag>,
    val events: List<UiEventCard>,
    val users: List<UiPersonCard>
)
