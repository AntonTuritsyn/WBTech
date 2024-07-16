package com.turitsynanton.android.wbtech.navigation.bottom

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.ui.components.BottomIcon
import com.turitsynanton.android.wbtech.ui.theme.NeutralWhite

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Navigation.MeetingsScreen, Navigation.CommunitiesScreen, Navigation.MoreScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar(
        contentColor = NeutralWhite,
        containerColor = NeutralWhite
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navBackStackEntry = navBackStackEntry,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Navigation, navBackStackEntry: NavBackStackEntry?, navController: NavHostController
) {
    val isSelected =
        navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } ?: false
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            if (!isSelected) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    restoreState = true
                    launchSingleTop = true
                }
            }
        },
        icon = {
            BottomIcon(isPressed = isSelected, title = screen.title, icon = screen.icon)
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemColors(
            selectedIconColor = Color.Unspecified,
            selectedTextColor = NeutralWhite,
            selectedIndicatorColor = NeutralWhite,
            unselectedIconColor = Color.Unspecified,
            unselectedTextColor = NeutralWhite,
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified
        )
    )
    Log.d("TAG", "isSelected $isSelected")
}