package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMeetings


fun NavGraphBuilder.meetingScreenNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = Navigation.Meetings.route,
        route = Navigation.MeetingsScreen.route
    ) {
        composable(route = Navigation.Meetings.route) {
            ScreenMeetings(modifier, tabs1, meetings) {
                navController.navigate(Navigation.MeetingDetails.route)
            }
        }
        composable(route = Navigation.MeetingDetails.route) {
            ScreenMeetingDetails(modifier, meetingTags = meetingTag, navController)
        }
    }
}