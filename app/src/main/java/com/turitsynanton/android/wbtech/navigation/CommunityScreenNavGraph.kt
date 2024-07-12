package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenCommunityDetails

fun NavGraphBuilder.communityScreenNavGraph(
    navController: NavHostController, modifier: Modifier
) {
    navigation(
        startDestination = NavigationBottomBar.Communities.route,
        route = NavigationBottomBar.CommunitiesScreen.route
    ) {
        composable(route = NavigationBottomBar.Communities.route) {
            ScreenCommunities(modifier = modifier, communityList = communities) {
                navController.navigate(NavigationCommunityDetails.CommunityDetails.route)
            }
        }
        composable(route = NavigationCommunityDetails.CommunityDetails.route) {
            ScreenCommunityDetails(meetingsList = meetings, modifier = modifier, navController) {
            }
        }
    }
}