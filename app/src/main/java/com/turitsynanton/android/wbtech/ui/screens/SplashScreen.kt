package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.navigation.NavigationItems
import com.turitsynanton.android.wbtech.navigation.SplashScreen
import com.turitsynanton.android.wbtech.ui.components.LottieAnimation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxHeight()) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.lottie)
        )
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)

        com.airbnb.lottie.compose.LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navController.navigate(NavigationItems.Meetings.route) {
                popUpTo(SplashScreen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}