package com.turitsynanton.android.wbtech.navigation

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
    //      participants
    Participants("participants"),
    ParticipantsDetailsScreen("participantsDetailsScreen"),
    //      subscribers
    Subscribers("subscribers"),
    SubscribersDetailsScreen("subscribersDetailsScreen"),
    //      userProfile
    Profile("profile"),
    ProfileScreen("profileDetailsScreen"),
    //      myProfile
    MyProfile("myProfile"),
    MyProfileScreen("myProfileDetailsScreen"),
    //      permissions
    Permissions("permissions"),
    PermissionsScreen("permissionsScreen"),
    //      registration
    Registration("registration"),
    RegistrationScreen("registrationScreen"),
    //      addPhoto
    AddPhoto("addPhoto"),
    AddPhotoScreen("addPhotoScreen")
}