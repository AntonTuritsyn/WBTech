package com.turitsynanton.android.wbtech.ui.drafts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf("screen1") }

    when (currentScreen) {
        "screen1" -> Screen1 { currentScreen = "screen2" }
        "screen2" -> Screen2 { currentScreen = "screen1" }
    }
}

@Composable
fun Screen1(onNavigate: () -> Unit) {
    Column {
        Text("This is Screen 1")
        Button(onClick = onNavigate) {
            Text("Go to Screen 2")
        }
    }
}

@Composable
fun Screen2(onNavigate: () -> Unit) {
    Column {
        Text("This is Screen 2")
        Button(onClick = onNavigate) {
            Text("Go to Screen 1")
        }
    }
}
// варианты тут:
// https://chatgpt.com/share/9385bd62-48b1-4b6c-8e5d-d49997604e08