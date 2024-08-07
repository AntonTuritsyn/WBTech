package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.Navigation

@Composable
internal fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxHeight()) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.lottie)
        )
        val logoAnimationState = animateLottieCompositionAsState(composition = composition, speed = 1.5f)

        LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navController.navigate(Navigation.Meetings.route) {
                popUpTo(Navigation.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}