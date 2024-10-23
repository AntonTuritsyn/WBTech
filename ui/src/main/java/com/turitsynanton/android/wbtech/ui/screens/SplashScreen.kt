package com.turitsynanton.android.wbtech.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.navigation.Navigation

@Composable
internal fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxHeight()) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.lottie)
        )
        val logoAnimationState = animateLottieCompositionAsState(composition = composition, speed = 3f)

        val activity = LocalContext.current as Activity

        /*val locationViewModel: LocationViewModel = koinViewModel { parametersOf(activity) }
        val locationPermissionGranted by locationViewModel.locationPermissionGrantedFlow().collectAsStateWithLifecycle()*/

        LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {


//            if (locationPermissionGranted == true) {
                navController.navigate(Navigation.EventsList.route) {
                    popUpTo(Navigation.Splash.route) {
                        inclusive = true
                    }
//                }
            } /*else {
                navController.navigate(Navigation.PermissionsScreen.route) {
                    Log.d("TAG", "Location permission granted: $locationPermissionGranted")
                    popUpTo(Navigation.PermissionsScreen.route) {
                        inclusive = true
                    }
                }
            }*/
        }
    }
}