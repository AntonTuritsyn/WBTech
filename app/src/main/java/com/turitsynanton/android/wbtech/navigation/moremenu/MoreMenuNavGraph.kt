package com.turitsynanton.android.wbtech.navigation.moremenu

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.ui.drafts.CustomViews
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenProfile
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenAddName
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenCode
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenPhone

fun NavGraphBuilder.moreMenuNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = Navigation.More.route,
        route = Navigation.MoreScreen.route
    ) {
        composable(route = Navigation.More.route) {
            ScreenMoreMenu(
                modifier = modifier,
                navHostController = navController,
                onProfileScreen = { navController.navigate(Navigation.ScreenMore.route) },
                onThemeScreen = { navController.navigate(Navigation.ScreenTheme.route) },
                onNotificationScreen = { navController.navigate(Navigation.ScreenNotification.route) },
                onSafetyScreen = { /*navController.navigate(Navigation.ScreenSafety.route)*/ }
                )
        }
        composable(route = Navigation.ScreenMore.route) {
            ScreenProfile()
        }
        composable(route = Navigation.ScreenTheme.route) {
            CustomViews()
        }
        composable(route = Navigation.ScreenNotification.route) {
            ScreenPhone(navController = navController)
        }
        composable(route = "${Navigation.ScreenCode.route}/{phoneNum}") { stackEntry ->
            val phoneNum = stackEntry.arguments?.getString("phoneNum")
            if (phoneNum != null) {
                ScreenCode(navController = navController, phoneNum = phoneNum)
            }
        }
        composable(route = Navigation.ScreenAddName.route) {
            ScreenAddName(navController = navController) {
            }
        }
    }
}