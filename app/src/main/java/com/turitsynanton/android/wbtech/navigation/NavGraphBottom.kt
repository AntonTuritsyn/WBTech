package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.data.tabs2
import com.turitsynanton.android.wbtech.ui.components.LottieAnimation
import com.turitsynanton.android.wbtech.ui.screens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetings
import com.turitsynanton.android.wbtech.ui.screens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.ScreenProfile
import com.turitsynanton.android.wbtech.ui.screens.SplashScreen

@Composable
fun NavGraphBottom(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = /*NavigationItems.Meetings.route*/SplashScreen.Splash.route)
    {
        composable(route = SplashScreen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = "${NavigationItems.Meetings.route}") {
            ScreenMeetings(modifier, tabs1, meetings) {
                navController.navigate(NavigationMeetingDetails.MeetingDetails.route)
            }
        }
        composable(route = NavigationItems.Communities.route) {
            ScreenCommunities(modifier, communities) {
                navController.navigate(NavigationCommunityDetails.CommunityDetails.route)
            }
        }
        composable(route = NavigationItems.More.route) {
            ScreenMoreMenu(modifier)
        }
        meetingDetails(navController = navController, modifier)
    }
}

fun NavGraphBuilder.meetingDetails(navController: NavHostController, modifier: Modifier) {
    navigation(
        route = NavigationMeetingDetails.MeetingDetails.route,
        startDestination = NavigationItems.Meetings.route
    ) {
        composable(route = NavigationItems.Meetings.route) {
            ScreenMeetingDetails(modifier, meetingTags = meetingTag, navController)
        }
    }
}

sealed class SplashScreen(var route: String) {
    data object Splash : SplashScreen("splashScreen")
}

sealed class NavigationMeetingDetails(var route: String) {
    data object MeetingDetails : NavigationMeetingDetails("meetingDetails")
}

sealed class NavigationCommunityDetails(var route: String) {
    data object CommunityDetails : NavigationCommunityDetails("communityDetails")
}