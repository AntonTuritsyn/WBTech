package com.turitsynanton.android.wbtech.navigation

import com.turitsynanton.android.ui.R

enum class Navigation(var route: String, var icon: Int = 0, var title: String = "") {
    //    splash
    Splash("splashScreen"),

    //    meetings
    Meetings("meetings", R.drawable.ic_meetings, "Встречи"),
    MeetingsScreen("meetingsScreen", R.drawable.ic_meetings, "Встречи"),
    MeetingDetails("meetingDetails"),

    //    communities
    Communities("communities", R.drawable.ic_society, "Сообщества"),
    CommunitiesScreen("communitiesScreen", R.drawable.ic_society, "Сообщества"),
    CommunityDetails("communityDetails"),

    //    more
    More("more", R.drawable.ic_more, "Ещё"),
    MoreScreen("moreScreen", R.drawable.ic_more, "Ещё"),
    ScreenMore("profileScreen"),
    ScreenTheme("themeScreen"),
    ScreenNotification("notificationScreen"),
    ScreenSafety("safetyScreen"),

    //    auth
    ScreenCode("codeScreen"),
    ScreenAddName("addNameScreen")
}