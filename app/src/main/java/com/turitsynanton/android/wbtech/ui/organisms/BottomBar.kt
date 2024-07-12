package com.turitsynanton.android.wbtech.ui.organisms

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turitsynanton.android.wbtech.navigation.NavigationBottomBar
import com.turitsynanton.android.wbtech.navigation.NavigationState
import com.turitsynanton.android.wbtech.ui.components.BottomIcon

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        NavigationBottomBar.Meetings, NavigationBottomBar.Communities, NavigationBottomBar.More
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
    screen: NavigationBottomBar, navDestination: NavDestination?, navController: NavHostController
) {
    /*val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination*/

    val isSelected = navDestination?.hierarchy?.any { it.route == screen.route } == true
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            if (!isSelected) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
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
            selectedTextColor = Color(0xFFFFFFFF),
            selectedIndicatorColor = Color(0xFFFFFFFF),
            unselectedIconColor = Color.Unspecified,
            unselectedTextColor = Color(0xFFFFFFFF),
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified
        )
    )
    Log.d("TAG", "isSelected $isSelected")
}