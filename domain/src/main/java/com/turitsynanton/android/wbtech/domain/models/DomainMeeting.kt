package com.turitsynanton.android.wbtech.domain.models

data class DomainMeeting(
    val id: String,
    val name: String,
    val date: String,
    val city: String,
    val ended: Boolean,
    val tags: List<MeetingTag>,
//    val icon: String
)