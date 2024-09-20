package com.turitsynanton.android.wbtech.domain.models

data class DomainEvent(
    val id: String,
    val name: String,
    val date: String,
    val city: String,
    val description: String,
    val host: DomainUser,
    val participants: List<DomainUser>,
    val tags: List<DomainTag>,
    val image: String
)
