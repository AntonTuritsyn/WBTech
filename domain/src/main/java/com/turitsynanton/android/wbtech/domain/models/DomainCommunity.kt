package com.turitsynanton.android.wbtech.domain.models

data class DomainCommunity(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val tags: List<DomainTag>,
    val users: List<DomainUser>,
    val events: List<DomainEvent>
)
