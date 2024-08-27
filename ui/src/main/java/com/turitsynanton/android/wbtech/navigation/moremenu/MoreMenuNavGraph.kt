package com.turitsynanton.android.wbtech.navigation.moremenu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenProfile
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenAddName
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenCode
import com.turitsynanton.android.wbtech.ui.screens.authscreens.ScreenPhone
import com.turitsynanton.android.wbtech.uinew.components.SearchFieldNew

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.moreMenuNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Navigation.More.route,
        route = Navigation.MoreScreen.route
    ) {
        composable(route = Navigation.More.route) {
            ScreenMoreMenu(
                modifier = Modifier,
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
            /*CommunityRecommends(
                modifier = Modifier,
                recommendationName = "Сообщества для тестировщиков",
                communitiesList = commmunitiesList,
                subscribeButtonStyle = SubscribeButtonStyle.Default
            )*/
//            ScreenEvents(eventsList = generateEvents())
            SearchFieldNew(modifier = Modifier, query = "")
        }
        composable(route = Navigation.ScreenNotification.route) {
            ScreenPhone(navController = navController)
        }
        composable(route = "${Navigation.ScreenCode.route}/{phoneNum}") { stackEntry ->
            stackEntry.arguments?.getString("phoneNum")?.let {
                ScreenCode(navController = navController, phoneNum = it)
            }
        }
        composable(route = Navigation.ScreenAddName.route) {
            ScreenAddName(navController = navController) {
            }
        }
    }
}