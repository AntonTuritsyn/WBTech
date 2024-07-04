package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turitsynanton.android.wbtech.navigation.NavigationItems
import com.turitsynanton.android.wbtech.ui.components.BottomIcon

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        NavigationItems.Meetings, NavigationItems.Communities, NavigationItems.More
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navBackStackEntry?.destination
    NavigationBar(
        contentColor = Color(0xFFFFFFFF),
        containerColor = Color(0xFFFFFFFF)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen, navDestination = currentDestination, navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationItems, navDestination: NavDestination?, navController: NavHostController
) {
    /*val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination*/

    val isSelected = /*currentDestination?.route == screen.route*/ navDestination?.route == screen.route
    NavigationBarItem(
        selected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        icon = {
            BottomIcon(isPressed = isSelected, title = screen.title, icon = screen.icon)
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemColors(
            selectedIconColor = Color.Unspecified,
            selectedTextColor = Color(0xFFFFFFFF),
            selectedIndicatorColor = Color(0xFFFFFFFF),
            unselectedIconColor = Color.Unspecified,
            unselectedTextColor = Color(0xFFFFFFFF),
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified
        )
    )
}