package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.dataMeetingTags
import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.repository.meetingTags
import com.turitsynanton.android.wbtech.data.storage.models.DataMeeting
import com.turitsynanton.android.wbtech.domain.models.Meeting
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenCommunityDetails

fun NavGraphBuilder.communityScreenNavGraph(
    navController: NavHostController, modifier: Modifier
) {
    navigation(
        startDestination = Navigation.Communities.route,
        route = Navigation.CommunitiesScreen.route
    ) {
        composable(route = Navigation.Communities.route) {
            ScreenCommunities(modifier = modifier) {
                navController.navigate(Navigation.CommunityDetails.route)
            }
        }
        composable(route = Navigation.CommunityDetails.route) {
            ScreenCommunityDetails(
                meetingsList = meetings,
                modifier = modifier,
                navController = navController
            ) {
            }
        }
    }
}

val meetings = listOf(
    Meeting(
        1,
        "Developer meet",
        "11.12.2023",
        "Moscow",
        true,
        meetingTags
    ),
    Meeting(
        2,
        "Kotlin meet",
        "11.05.2024",
        "Volgograd",
        true,
        meetingTags
    ),
    Meeting(
        3,
        "Android meet",
        "11.08.2024",
        "Kazan",
        false,
        meetingTags
    )
)