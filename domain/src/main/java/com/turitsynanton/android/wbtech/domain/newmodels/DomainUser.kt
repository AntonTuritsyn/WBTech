package com.turitsynanton.android.wbtech.domain.newmodels

data class DomainUser(
    val id: String,
    val name: String,
    val city: String,
    val description: String,
    val tags: List<DomainTag>,
    val picture: String
)