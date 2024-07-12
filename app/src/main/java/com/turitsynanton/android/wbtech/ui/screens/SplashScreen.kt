package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.navigation.NavigationBottomBar
import com.turitsynanton.android.wbtech.navigation.NavigationState
import com.turitsynanton.android.wbtech.navigation.SplashScreen

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxHeight()) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.lottie)
        )
        val logoAnimationState = animateLottieCompositionAsState(composition = composition, speed = 1.5f)

        LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
//            navigationState.navigateTo(NavigationBottomBar.Meetings.route)
            navController.navigate(NavigationBottomBar.Meetings.route) {
                popUpTo(SplashScreen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}