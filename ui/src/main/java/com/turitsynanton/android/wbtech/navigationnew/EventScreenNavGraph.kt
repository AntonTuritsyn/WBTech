package com.turitsynanton.android.wbtech.navigationnew

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.uinew.screens.participants.ScreenParticipants
import com.turitsynanton.android.wbtech.uinew.screens.userprofile.ScreenProfileUser
import com.turitsynanton.android.wbtech.uinew.screens.communitydetails.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventdetails.ScreenEventDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventslist.ScreenEventsList
import com.turitsynanton.android.wbtech.uinew.screens.subscribers.ScreenSubscribers

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.eventScreenNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Navigation.EventsList.route,
        route = Navigation.EventsListScreen.route
    ) {
        composable(route = Navigation.EventsList.route) {
            ScreenEventsList(
                onProfileClick = {
//                    пока не существует
//                    navController.navigate("${Navigation.MyProfileScreen.route}")
                },
                onEventClick = { eventId ->
                    navController.navigate("${Navigation.EventDetailsScreen.route}/${eventId}")
                },
                onCommunityClick = { communityId ->
                    navController.navigate("${Navigation.CommunityDetailsScreen.route}/${communityId}")
                },
                onSubscribeClick = {},
                onUserClick = {

                }
            )
        }
        composable(route = "${Navigation.EventDetailsScreen.route}/{eventid}") { stackEntry ->
            stackEntry.arguments?.getString("eventid")?.let { id ->
                ScreenEventDetails(
                    eventId = id,
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {},
                    onHostClick = {},
                    onParticipantsClick = { eventId ->
                        navController.navigate("${Navigation.ParticipantsDetailsScreen.route}/${eventId}")
                    },
                    onOganizerClick = { communityId ->
                        navController.navigate("${Navigation.CommunityDetailsScreen.route}/${communityId}")
                    },
                    onEventClick = {
                        navController.navigate("${Navigation.EventDetailsScreen.route}/{id}")
                    },
                    onSignUpToEventClick = {
                        navController.navigate("${Navigation.ParticipantsDetailsScreen.route}/{id}")
                    }
                )
            }
        }
        composable(route = "${Navigation.CommunityDetailsScreen.route}/{communityid}") { stackEntry ->
            stackEntry.arguments?.getString("communityid")?.let { id ->
                ScreenCommunityDetails(
                    communityId = id,
                    onBackClick = { navController.popBackStack() },
                    onShareClick = {},
                    onSubscribeClick = {

                    },
                    onUsersClick = { communityId ->
                        navController.navigate("${Navigation.SubscribersDetailsScreen.route}/${communityId}")
                    },
                    onEventClick = { eventId ->
                        navController.navigate("${Navigation.EventDetailsScreen.route}/$eventId")
                    })
            }
        }
        composable(route = "${Navigation.ParticipantsDetailsScreen.route}/{eventId}") { stackEntry ->
            stackEntry.arguments?.getString("eventId")?.let { id ->
                ScreenParticipants(
                    eventId = id,
                    onBackClick = { navController.popBackStack() },
                    onUserClick = { userId ->
                        navController.navigate("${Navigation.ProfileScreen.route}/${userId}")
                    }
                )
            }
        }
        composable(route = "${Navigation.SubscribersDetailsScreen.route}/{communityId}") { stackEntry ->
            stackEntry.arguments?.getString("communityId")?.let { id ->
                ScreenSubscribers(
                    communityId = id,
                    onBackClick = { navController.popBackStack() },
                    onUserClick = { userId ->
                        navController.navigate("${Navigation.ProfileScreen.route}/${userId}")
                    }
                )
            }
        }
        composable(route = "${Navigation.ProfileScreen.route}/{userId}") { stackEntry ->
            stackEntry.arguments?.getString("userId")?.let { id ->
                ScreenProfileUser(
                    userId = id,
                    eventsList = listOf(),
                    communitiesList = listOf(),
                    onBackClick = { navController.popBackStack() },
                    onEventClick = { /*TODO*/ },
                    onCommunityClick = { /*TODO*/ }) {

                }
            }
        }
    }
}