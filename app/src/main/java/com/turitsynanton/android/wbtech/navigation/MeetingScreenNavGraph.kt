package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetings


fun NavGraphBuilder.meetingScreenNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = NavigationBottomBar.Meetings.route,
        route = NavigationBottomBar.MeetingsScreen.route
    ) {
        composable(route = NavigationBottomBar.Meetings.route) {
            ScreenMeetings(modifier, tabs1, meetings) {
                navController.navigate(NavigationMeetingDetails.MeetingDetails.route)
            }
        }
        composable(route = NavigationMeetingDetails.MeetingDetails.route) {
            ScreenMeetingDetails(modifier, meetingTags = meetingTag, navController)
        }
    }
}