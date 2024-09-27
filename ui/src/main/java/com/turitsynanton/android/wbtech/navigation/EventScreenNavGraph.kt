package com.turitsynanton.android.wbtech.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.uinew.screens.addphoto.ScreenAddPhoto
import com.turitsynanton.android.wbtech.uinew.screens.participants.ScreenParticipants
import com.turitsynanton.android.wbtech.uinew.screens.userprofile.ScreenProfileUser
import com.turitsynanton.android.wbtech.uinew.screens.communitydetails.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventdetails.ScreenEventDetails
import com.turitsynanton.android.wbtech.uinew.screens.eventslist.ScreenEventsList
import com.turitsynanton.android.wbtech.uinew.screens.interests.ScreenAddInterests
import com.turitsynanton.android.wbtech.uinew.screens.myprofile.ScreenProfileMy
import com.turitsynanton.android.wbtech.uinew.screens.registration.Country
import com.turitsynanton.android.wbtech.uinew.screens.registration.ScreenRegistrationForEvent
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
                    navController.navigate("${Navigation.MyProfileScreen.route}")
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
                    onSubscribeToCommunityClick = {},
                    onHostClick = { userId ->
                        navController.navigate("${Navigation.ProfileScreen.route}/${userId}")
                    },
                    onParticipantsClick = { eventId ->
                        navController.navigate("${Navigation.ParticipantsDetailsScreen.route}/${eventId}")
                    },
                    onOrganizerClick = { communityId ->
                        navController.navigate("${Navigation.CommunityDetailsScreen.route}/${communityId}")
                    },
                    onEventClick = { eventId ->
                        navController.navigate("${Navigation.EventDetailsScreen.route}/${eventId}")
                    },
                    onSignUpToEventClick = {
                        navController.navigate("${Navigation.RegistrationScreen.route}/${id}")
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
                    onBackClick = { navController.popBackStack() },
                    onEventClick = { eventId->
                        navController.navigate("${Navigation.EventDetailsScreen.route}/${eventId}")
                    },
                    onCommunityClick = { communityId ->
                        navController.navigate("${Navigation.CommunityDetailsScreen.route}/${communityId}")
                    }) {

                }
            }
        }
        composable(route = "${Navigation.MyProfileScreen.route}") {
            ScreenProfileMy(
                onBackClick = { navController.popBackStack() },
                onEditClick = {},
                onSaveClick = {},
                onChangePhoto = {
//                    navController.navigate("${Navigation.AddPhotoScreen.route}")
                },
                onEventClick = { eventId->
                    navController.navigate("${Navigation.EventDetailsScreen.route}/${eventId}")
                },
                onCommunityClick = { communityId ->
                    navController.navigate("${Navigation.CommunityDetailsScreen.route}/${communityId}")
                },
                onSocialClick = {},
                onLogOutClick = {}
            )
        }
        composable(route = "${Navigation.RegistrationScreen.route}/{eventId}") { stackEntry ->
            stackEntry.arguments?.getString("eventId")?.let { id ->
                ScreenRegistrationForEvent(
                    eventId = id,
                    selectedCountry = Country("Russia", "+7", R.drawable.flag_russia),
                    onCloseClick = {
                        navController.popBackStack()
                    }
                ) {
                    navController.navigate("${Navigation.EventsListScreen.route}") {
//                        дополнительно проверить работу
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
        composable(route = "${Navigation.AddPhotoScreen.route}") {
            ScreenAddPhoto()
            /*ScreenAddInterests(
                onTagClick = {},
                onSaveClick = {}
            )*/
        }
    }
}