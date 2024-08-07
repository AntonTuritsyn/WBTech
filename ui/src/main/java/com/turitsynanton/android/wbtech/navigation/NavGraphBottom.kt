package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turitsynanton.android.wbtech.navigation.communityScreenNavGraph
import com.turitsynanton.android.wbtech.navigation.meetingScreenNavGraph
import com.turitsynanton.android.wbtech.navigation.moremenu.moreMenuNavGraph
import com.turitsynanton.android.wbtech.ui.Navigation
import com.turitsynanton.android.wbtech.ui.screens.SplashScreen

@Composable
fun NavGraphBottom(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.Splash.route
    )
    {
        composable(route = Navigation.Splash.route) {
            SplashScreen(navController = navController)
        }
        meetingScreenNavGraph(
            navController = navController
        )
        communityScreenNavGraph(navController = navController)
        moreMenuNavGraph(navController = navController)
    }
}
