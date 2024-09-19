package com.turitsynanton.android.wbtech.domain.models

data class DomainProfile(
    val id: String,
    val name: String,
    val city: String,
    val phone: String,
    val description: String,
    val tags: List<DomainTag>,
    val icon: String
)
