package com.turitsynanton.android.wbtech

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.navigation.NavGraphBottom
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.navigationnew.NavGraph
import com.turitsynanton.android.wbtech.ui.bottombar.BottomBar
import com.turitsynanton.android.wbtech.ui.theme.WBTechTheme
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        MapKitFactory.initialize(this)
        setContent {
            WBTechTheme {
                val navController = rememberNavController()
                val showBottomBar = remember {
                    mutableStateOf(false)
                }
                NavGraph(navController = navController)
                /*navController.addOnDestinationChangedListener { _, destination, _ ->
                    showBottomBar.value = destination.route != Navigation.Splash.route
                }*/

                /*Scaffold(
                    bottomBar = {
                        if (showBottomBar.value)
                            BottomBar(
                                navController = navController
                            )
                    }
                ) { innerPading ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPading)
                    ) {
                        NavGraphBottom(
                            navController = navController
                        )
                    }
                }*/
            }
        }
    }

//    для яндекс карт
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}

