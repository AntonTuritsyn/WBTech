package com.turitsynanton.android.wbtech.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turitsynanton.android.wbtech.permissions.LocationPermissionScreen
import com.turitsynanton.android.wbtech.ui.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
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
        composable(route = Navigation.PermissionsScreen.route) {
            LocationPermissionScreen(navController = navController)
        }
        eventScreenNavGraph(navController = navController)
    }
}