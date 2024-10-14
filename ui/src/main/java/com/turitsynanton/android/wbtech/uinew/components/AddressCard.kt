package com.turitsynanton.android.wbtech.uinew.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@Composable
internal fun AddressCard(
    modifier: Modifier,
    address: String,
//    onBackClick: (Unit) -> Unit
) {
    var mapView: MapView? by remember { mutableStateOf(null) }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = "Севкабель Порт, Кожевенная линия, 40,",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
        )
        SimpleTextField(
            modifier = Modifier,
            text = "Приморская",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF000000),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Box(modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))) {
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
                        mapView = this
                        /*val imageProvider = ImageProvider.fromResource(context, R.drawable.ic_dollar_pin)
                    val placemarkObject = map.mapObjects.addPlacemark(POINT).apply {
                        setIcon(imageProvider)
                    }*/
                        /*placemarkObject.addTapListener { _, point ->
                        // Replace with your Toast code
                        true
                    }*/
//                        setNoninteractive(true)
                    }
                },
                update = { mapView ->
                    // Perform any updates to the MapView here
                }
            )
            DisposableEffect(Unit) {
                onDispose {
                    Log.d("TAG", "onDispose1: ${mapView}")
                    mapView?.onStop()
                    Log.d("TAG", "onDispose2: ${mapView}")
                    MapKitFactory.getInstance().onStop()
                    Log.d("TAG", "onDispose3: ${mapView}")
                }
            }
        }
    }
}

private val POINT = Point(55.751280, 37.629720)