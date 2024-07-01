package com.turitsynanton.android.wbtech.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.ui.screens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.ScreenMeetings
import com.turitsynanton.android.wbtech.ui.screens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.ScreenProfile

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = NavigationItems.Meetings.route) {
        composable(route = NavigationItems.Meetings.route) {
            ScreenMeetings(modifier, tabs1, meetings)
        }
        composable(route = NavigationItems.Communities.route) {
            ScreenCommunities(modifier)
        }
        composable(route = NavigationItems.More.route) {
            ScreenMoreMenu(navController, modifier) {
                /*navController.navigate(route = NavigationMoreMenu.Profile.route)*/
            }
        }
//        скорее всего удалю
        /*composable(route = NavigationMoreMenu.Profile.route) {
            ScreenProfile()
        }*/
    }
}