package com.turitsynanton.android.wbtech

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.navigation.NavGraph
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

