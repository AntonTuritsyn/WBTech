package com.turitsynanton.android.wbtech

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.navigation.NavGraphBottom
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.navigationnew.NavGraph
import com.turitsynanton.android.wbtech.ui.bottombar.BottomBar
import com.turitsynanton.android.wbtech.ui.theme.WBTechTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WBTechTheme {
                val navController = rememberNavController()
                val showBottomBar = remember {
                    mutableStateOf(false)
                }
                /*navController.addOnDestinationChangedListener { _, destination, _ ->
                    showBottomBar.value = destination.route != Navigation.Splash.route
                }*/
                NavGraph(navController = navController)
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
}