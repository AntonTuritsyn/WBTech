package com.turitsynanton.android.wbtech.navigation

sealed class NavigationMoreMenu(var route: String) {
    data object Profile : NavigationMoreMenu("profile")
    data object MyMeetings : NavigationMoreMenu("myMeetings")
}