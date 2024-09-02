package com.turitsynanton.android.wbtech.navigationnew

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.uinew.screens.communitydetails.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventdetails.ScreenEventDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventslist.ScreenEventsList
import com.turitsynanton.android.wbtech.uinew.screens.ScreenParticipants
import com.turitsynanton.android.wbtech.uinew.screens.ScreenProfile

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.eventScreenNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Navigation.EventsList.route,
        route = Navigation.EventsListScreen.route
    ) {
        composable(route = Navigation.EventsList.route) {
            ScreenEventsList(
//                eventTopList = generateEvents(),
                eventsUpcomingList = generateEvents(),
//                communitiesList = generateCommunitiesList(),
//                eventsList = generateEvents(),
                onProfileClick = {
                    navController.navigate("${Navigation.MyProfileScreen.route}")
                },
                onEventClick = {
                    navController.navigate("${Navigation.EventDetailsScreen.route}/${it}")
                },
                onCommunityClick = {
                    navController.navigate("${Navigation.CommunityDetailsScreen.route}/${it}")
                },
                onSubscribeClick = {},
                onUserClick = {

                }
            )
        }
        composable(route = "${Navigation.EventDetailsScreen.route}/{id}") { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenEventDetails(
                    eventId = id,
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {},
                    onHostClick = {},
                    onParticipantsClick = {
                        navController.navigate(Navigation.ParticipantsDetailsScreen.route)
                    },
                    onOganizerClick = {
                        navController.navigate("${Navigation.CommunityDetailsScreen.route}/{id}")
                    },
                    onEventClick = {
                        navController.navigate("${Navigation.EventDetailsScreen.route}/{id}")
                    },
                    onSignUpToEventClick = {})
            }
        }
        composable(route = "${Navigation.CommunityDetailsScreen.route}/{id}") { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenCommunityDetails(
                    communityId = id,
                    community = generateCommunity(),
                    eventList = generateEvents(),
                    pastEventList = listOf(),
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {},
                    onUsersClick = {
                        navController.navigate(Navigation.ParticipantsDetailsScreen.route)
                    },
                    onEventClick = {
                        navController.navigate("${Navigation.EventDetailsScreen.route}/{id}")
                    })
            }
        }
        composable(route = Navigation.ParticipantsDetailsScreen.route) {
            ScreenParticipants(
                participants = generateUsersList(),
                onBackClick = { navController.popBackStack() },
                onUserClick = {}
            )
//            MapScreen()
        }
        composable(route = Navigation.MyProfileScreen.route) {
            ScreenProfile(
                user = generateUsersList().first(),
                eventsList = listOf(),
                communitiesList = /*generateCommunitiesList()*/listOf(),
                onBackClick = { /*TODO*/ },
                onEventClick = { /*TODO*/ },
                onCommunityClick = { /*TODO*/ }) {

            }
        }
    }
}