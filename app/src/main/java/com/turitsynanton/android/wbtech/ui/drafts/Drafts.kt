package com.turitsynanton.android.wbtech.ui.drafts

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ToTry() {
    var isPressed by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()
    OutlinedButton(
        onClick = {
            coroutineScope.launch {
                isPressed = !isPressed
                delay(300)
                isPressed = !isPressed
            }
        },
        modifier = Modifier
            .border(
                width = 4.dp,
                color = if (isPressed) Color(0xFFDFCBF5) else Color.Unspecified,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFF9A41FE), containerColor = Color.Unspecified
        ),
        border = ButtonDefaults
            .outlinedButtonBorder
            .copy(brush = SolidColor(Color(0xFF541896))),
    ) {
        Text(text = "Button")
    }
}

@Composable
fun OverlappingImagesRow(
    images: List<ImageBitmap>
) {
    LazyRow(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(-24.dp),
        reverseLayout = true
    ) {
        items(images.size) { index ->
            Image(
                bitmap = images[index],
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, Color(0xFFD0BCFF), RoundedCornerShape(16.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOverlappingImagesRow() {
    val images = List(5) {
        ImageBitmap.imageResource(id =  R.drawable.ic_meeting,)
    }
    OverlappingImagesRow(images = images)
}