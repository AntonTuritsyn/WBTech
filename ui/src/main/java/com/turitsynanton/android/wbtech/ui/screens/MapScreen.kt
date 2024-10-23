package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@Composable
fun MapScreen() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    val map = mapWindow.map
                    map.move(
                        CameraPosition(
                            POINT,
                            17.0f,
                            150.0f,
                            30.0f
                        )
                    )
                    /*val imageProvider = ImageProvider.fromResource(context, R.drawable.ic_dollar_pin)
                    val placemarkObject = map.mapObjects.addPlacemark(POINT).apply {
                        setIcon(imageProvider)
                    }*/
                    /*placemarkObject.addTapListener { _, point ->
                        // Replace with your Toast code
                        true
                    }*/
                    setNoninteractive(true)
                    screenshot
                }
            },
            update = { mapView ->
                // Perform any updates to the MapView here
            }
        )

        Column {
            val mapkitVersion = MapKitFactory.getInstance().version
            BasicText(text = "MapKit Version: $mapkitVersion")
        }
    }
}
private val POINT = Point(55.751280, 37.629720)