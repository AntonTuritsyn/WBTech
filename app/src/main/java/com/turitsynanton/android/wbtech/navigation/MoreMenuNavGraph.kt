package com.turitsynanton.android.wbtech.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.ui.drafts.CustomViews
import com.turitsynanton.android.wbtech.ui.screens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.ScreenProfile

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
                onThemeDcreen = { navController.navigate(NavigationScreenMore.ScreenTheme.route) }
                )
        }
        composable(route = NavigationScreenMore.ScreenMore.route) {
            ScreenProfile()
        }
        composable(route = NavigationScreenMore.ScreenTheme.route) {
            CustomViews()
        }
    }
}

sealed class NavigationScreenMore(var route: String) {
    data object ScreenMore : NavigationScreenMore("profileScreen")
    data object ScreenTheme : NavigationScreenMore("themeScreen")
}