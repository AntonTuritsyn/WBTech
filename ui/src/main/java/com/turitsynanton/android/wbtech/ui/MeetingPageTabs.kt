package com.turitsynanton.android.wbtech.ui

sealed class MeetingPageTabs {
    object LeftTab: MeetingPageTabs()
    object RightTab: MeetingPageTabs()
}

val pages = listOf(
    MeetingPageTabs.LeftTab,
    MeetingPageTabs.RightTab
)