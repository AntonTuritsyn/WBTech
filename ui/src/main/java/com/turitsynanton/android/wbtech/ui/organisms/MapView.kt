package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.turitsynanton.android.ui.R

@Composable
internal fun MapView() {
    var isDialogOpen by remember {
        mutableStateOf(false)
    }

//    тест рандомного изображения из сети
    val imageUrl = "https://api.api-ninjas.com/v1/randomimage?category="
    val apiKey = "mQP54MwvQ5OymmGfGKY+Tw==uM5bQlAWjr6RWAte"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .addHeader("X-Api-Key", apiKey)
        .addHeader("Accept", "image/jpg")
        .build()
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                color = Color.Unspecified
            ),
        shape = RoundedCornerShape(30.dp)
//        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = /*painterResource(id = R.drawable.map)*/painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { isDialogOpen = !isDialogOpen }
        )
    }

    if (isDialogOpen) {
        Dialog(onDismissRequest = { isDialogOpen = !isDialogOpen }) {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .clickable(interactionSource = interactionSource,
                        indication = null,
                        onClick = { isDialogOpen = !isDialogOpen })
            ) {
                ZoomImage(painter = painter)
            }
        }
    }
}

@Composable
internal fun ZoomImage(painter: Painter) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offsetX += pan.x
                    offsetY += pan.y
                }
            }
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun Prew() {
    MapView()
}