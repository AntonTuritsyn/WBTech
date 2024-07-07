package com.turitsynanton.android.wbtech.data

import android.nfc.Tag

data class Meeting(
    val id: Long,
    val name: String,
    val date: String,
    val city: String,
    val ended: Boolean,
    val tags: List<MeetingTag>,
//    val icon: String
)
