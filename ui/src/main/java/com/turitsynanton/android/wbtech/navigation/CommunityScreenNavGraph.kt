package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.ui.Navigation
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
            ScreenCommunities(modifier = modifier, onClick = {
                navController.navigate("${Navigation.CommunityDetails.route}/${it}")
            })
        }
        composable(
            route = "${Navigation.CommunityDetails.route}/{id}"
        ) { stackEntry ->
            stackEntry.arguments?.getString("id")?.let { id ->
                ScreenCommunityDetails(
                    modifier = modifier,
                    navController = navController,
                    communityId = id
                ) {
                }
            }
        }
    }
}