package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turitsynanton.android.wbtech.ui.screens.SplashScreen

@Composable
fun NavGraphBottom(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreen.Splash.route
    )
    {
        composable(route = SplashScreen.Splash.route) {
            SplashScreen(navController = navController)
        }
        meetingScreenNavGraph(
            navController = navController,
            modifier = modifier
        )
        communityScreenNavGraph(navController = navController, modifier = modifier)
        moreMenuNavGraph(navController = navController, modifier = modifier)
    }
}

sealed class SplashScreen(var route: String) {
    data object Splash : SplashScreen("splashScreen")
}

sealed class NavigationMeetingDetails(var route: String) {
    data object MeetingDetails : NavigationMeetingDetails("meetingDetails")
}

sealed class NavigationCommunityDetails(var route: String) {
    data object CommunityDetails : NavigationCommunityDetails("communityDetails")
}
