package com.turitsynanton.android.wbtech.permissions

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.turitsynanton.android.wbtech.navigationnew.Navigation
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun LocationPermissionScreen(
//    locationViewModel: LocationViewModel = koinViewModel(),
    navController: NavController
) {

//    фигня для попробовать


    val activity = LocalContext.current as Activity

    // Retrieve ViewModel with parameters
    val locationViewModel: LocationViewModel = koinViewModel { parametersOf(activity) }
    val locationPermissionGranted by locationViewModel.locationPermissionGrantedFlow()
        .collectAsStateWithLifecycle()

    LaunchedEffect(locationPermissionGranted) {
        locationViewModel.checkLocationPermission()
        if (locationPermissionGranted == true) {
            navController.navigate(Navigation.EventsList.route) {
                popUpTo(Navigation.PermissionsScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    if (locationPermissionGranted != true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Необходимо разрешение для доступа к геолокации")
            Button(onClick = { locationViewModel.requestLocationPermission() }) {
                Text("Предоставить разрешения")
            }
        }
    }
}