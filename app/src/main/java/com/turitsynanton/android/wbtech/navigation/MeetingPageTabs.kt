package com.turitsynanton.android.wbtech.navigation

sealed class MeetingPageTabs {
    object LeftTab: MeetingPageTabs()
    object RightTab: MeetingPageTabs()
}

val pages = listOf(
    MeetingPageTabs.LeftTab,
    MeetingPageTabs.RightTab
)