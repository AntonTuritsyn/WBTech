package com.turitsynanton.android.wbtech.navigation

import com.turitsynanton.android.wbtech.R

sealed class Navigation(var route: String, var icon: Int = 0, var title: String = "") {
//    splash
    data object Splash : Navigation("splashScreen")
//    meetings
    data object Meetings : Navigation("meetings", R.drawable.ic_meetings, "Встречи")
    data object MeetingsScreen : Navigation("meetingsScreen", R.drawable.ic_meetings, "Встречи")
    data object MeetingDetails : Navigation("meetingDetails")
//    communities
    data object Communities : Navigation("communities", R.drawable.ic_society, "Сообщества")
    data object CommunitiesScreen : Navigation("communitiesScreen", R.drawable.ic_society, "Сообщества")
    data object CommunityDetails : Navigation("communityDetails")
//    more
    data object More : Navigation("more", R.drawable.ic_more, "Ещё")
    data object MoreScreen : Navigation("moreScreen", R.drawable.ic_more, "Ещё")
    data object ScreenMore : Navigation("profileScreen")
    data object ScreenTheme : Navigation("themeScreen")
    data object ScreenNotification : Navigation("notificationScreen")
    data object ScreenSafety : Navigation("safetyScreen")
//    auth
    data object ScreenCode : Navigation("codeScreen")
    data object ScreenAddName : Navigation("addNameScreen")
}