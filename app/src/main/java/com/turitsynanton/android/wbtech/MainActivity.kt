package com.turitsynanton.android.wbtech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.navigation.bottom.NavGraphBottom
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.navigation.bottom.BottomBar
import com.turitsynanton.android.wbtech.ui.theme.WBTechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WBTechTheme {
                val navController = rememberNavController()
                val showBottomBar = remember {
                    mutableStateOf(true)
                }
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    showBottomBar.value = destination.route != Navigation.Splash.route
                }
                Scaffold(
                    bottomBar = {
                        if (showBottomBar.value)
                            BottomBar(
                                navController = navController
                            )
                    }
                ) { innerPading ->
                    NavGraphBottom(
                        navController = navController,
                        modifier = Modifier.padding(innerPading)
                    )
                }
            }
        }
    }
}