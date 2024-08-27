package com.turitsynanton.android.wbtech.navigationnew

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.uinew.screens.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.uinew.screens.ScreenEventDetails
import com.turitsynanton.android.wbtech.uinew.screens.ScreenEventsList
import com.turitsynanton.android.wbtech.uinew.screens.ScreenParticipants

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.eventScreenNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Navigation.EventsList.route,
        route = Navigation.EventsListScreen.route
    ) {
        composable(route = Navigation.EventsList.route) {
            ScreenEventsList(
                eventTopList = generateEvents(),
                eventsUpcomingList = generateEvents(),
                communitiesList = generateCommunitiesList(),
                eventsList = generateEvents(),
                onProfileClick = {},
                onEventClick = {
                    navController.navigate("${Navigation.EventDetailsScreen.route}/${it}")
                },
                onCommunityClick = {
                    navController.navigate("${Navigation.CommunityDetailsScreen.route}/${it}")
                },
                onSubscribeClick = {},
                onUserClick = {
                    navController.navigate("${Navigation.ParticipantsDetailsScreen.route}/${it}")
                }
            )
            /*ScreenMeetings(modifier = Modifier, tabs = tabs1, onClick = {
                navController.navigate("${Navigation.EventDetails.route}/${it}")
            })*/
        }
        composable(route = "${Navigation.EventDetailsScreen.route}/{id}") { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenEventDetails(
                    eventId = id,
                    event = generateEvents().first(),
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {},
                    onHostClick = {},
                    onParticipantsClick = {},
                    onOganizerClick = {},
                    onEventClick = {},
                    onSignUpToEventClick = {})
            }
        }
        composable(route = "${Navigation.CommunityDetailsScreen.route}/{id}") { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenCommunityDetails(
                    communityId = id,
                    community = generateCommunity(),
                    eventList = generateEvents(),
                    pastEventList = generateEvents(),
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {},
                    onUsersClick = {},
                    onEventClick = {})
            }
        }
        composable(route = Navigation.ParticipantsDetailsScreen.route) {
            ScreenParticipants(
                participants = generateUsersList(),
                onBackClick = { navController.popBackStack() },
                onUserClick = {}
            )
        }
    }
}