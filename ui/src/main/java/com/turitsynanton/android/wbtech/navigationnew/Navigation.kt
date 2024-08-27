package com.turitsynanton.android.wbtech.navigationnew

enum class Navigation(var route: String) {
    //    splash
    Splash("splashScreen"),
    //      main
    EventsList("events"),
    EventsListScreen("eventsScreen"),
    EventDetailsScreen("eventDetailsScreen"),
    //      communities
    Community("communitys"),
    CommunityDetailsScreen("communityDetailsScreen"),
    //      people
    Participants("participants"),
    ParticipantsDetailsScreen("participantsDetailsScreen"),
    //      userProfile
    Profile("profile"),
    ProfileScreen("profileDetailsScreen"),
    //      myProfile
    MyProfile("myProfile"),
    MyProfileScreen("myProfileDetailsScreen"),
/*


    //    meetings
    Meetings("meetings", R.drawable.ic_meetings, "Встречи"),
    MeetingsScreen("meetingsScreen", R.drawable.ic_meetings, "Встречи"),
    MeetingDetails("meetingDetails"),

    //    more
    More("more", R.drawable.ic_more, "Ещё"),
    MoreScreen("moreScreen", R.drawable.ic_more, "Ещё"),
    ScreenMore("profileScreen"),
    ScreenTheme("themeScreen"),
    ScreenNotification("notificationScreen"),
    ScreenSafety("safetyScreen"),

    //    auth
    ScreenCode("codeScreen"),
    ScreenAddName("addNameScreen")*/
}