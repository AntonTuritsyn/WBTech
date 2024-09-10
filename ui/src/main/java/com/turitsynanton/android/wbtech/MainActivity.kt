package com.turitsynanton.android.wbtech

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.navigationnew.NavGraph
import com.turitsynanton.android.wbtech.permissions.LocationPermissionScreen
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

