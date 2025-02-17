package com.turitsynanton.android.wbtech.models

data class UiCommunityCard(
    val id: String,
    val name: String,
    val tags: List<UiTag>,
    val image: String,
    val isSubscribed: Boolean
)
