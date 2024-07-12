package com.turitsynanton.android.wbtech.navigation

sealed class NavigationMoreMenu(var route: String) {
    data object Profile : NavigationMoreMenu("profile")
    data object MyMeetings : NavigationMoreMenu("myMeetings")
    data object Theme : NavigationMoreMenu("theme")
    data object Notifications : NavigationMoreMenu("notifications")
    data object Safety : NavigationMoreMenu("safety")
    data object Memory : NavigationMoreMenu("memory")
}