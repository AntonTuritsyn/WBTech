package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.ui.drafts.CustomViews
import com.turitsynanton.android.wbtech.ui.screens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetings
import com.turitsynanton.android.wbtech.ui.screens.ScreenMoreMenu
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
