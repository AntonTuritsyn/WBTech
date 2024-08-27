package com.turitsynanton.android.wbtech.domain.newmodels

data class DomainCommunity(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val tags: List<DomainTag>,
    val users: List<DomainUser>
)
