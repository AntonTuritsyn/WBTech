package com.turitsynanton.android.wbtech.data.storage.models

internal data class DataMeeting(
    val id: String,
    val name: String,
    val date: String,
    val city: String,
    val ended: Boolean,
    val tags: List<DataMeetingTag>,
//    val icon: String
)
