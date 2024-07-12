package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.ui.drafts.CustomViews
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenProfile
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenCode
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenPhone

fun NavGraphBuilder.moreMenuNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = NavigationBottomBar.More.route,
        route = NavigationBottomBar.MoreScreen.route
    ) {
        composable(route = NavigationBottomBar.More.route) {
            ScreenMoreMenu(
                modifier = modifier,
                navHostController = navController,
                onProfileScreen = { navController.navigate(NavigationScreenMore.ScreenMore.route) },
                onThemeScreen = { navController.navigate(NavigationScreenMore.ScreenTheme.route) },
                onNotificationScreen = { navController.navigate(NavigationScreenMore.ScreenNotification.route) },
                onSafetyScreen = { navController.navigate(NavigationScreenMore.ScreenSafety.route) }
                )
        }
        composable(route = NavigationScreenMore.ScreenMore.route) {
            ScreenProfile()
        }
        composable(route = NavigationScreenMore.ScreenTheme.route) {
            CustomViews()
        }
        composable(route = NavigationScreenMore.ScreenNotification.route) {
            ScreenPhone()
        }
        composable(route = NavigationScreenMore.ScreenSafety.route) {
            ScreenCode()
        }
    }
}

sealed class NavigationScreenMore(var route: String) {
    data object ScreenMore : NavigationScreenMore("profileScreen")
    data object ScreenTheme : NavigationScreenMore("themeScreen")
    data object ScreenNotification : NavigationScreenMore("notificationScreen")
    data object ScreenSafety : NavigationScreenMore("safetyScreen")

}