package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMeetings

fun NavGraphBuilder.meetingScreenNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Navigation.Meetings.route,
        route = Navigation.MeetingsScreen.route
    ) {
        composable(route = Navigation.Meetings.route) {
            ScreenMeetings(modifier = Modifier, tabs = tabs1, onClick = {
                navController.navigate("${Navigation.MeetingDetails.route}/${it}")
            })
        }
        composable(route = "${Navigation.MeetingDetails.route}/{id}") { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenMeetingDetails(
                    modifier = Modifier,
                    meetingTags = meetingTags,
                    navController = navController,
                    meetingId = id
                ) {

                }
            }

        }
    }
}

//      временное решение
val meetingTags = listOf(
    MeetingTag(
        "Java"
    ),
    MeetingTag(
        "Kotlin"
    ),
    MeetingTag(
        "Android"
    )
)