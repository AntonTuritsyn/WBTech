package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyFilterChip(modifier: Modifier, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(20.dp)
            .background(
                Color(0xFFF5ECFF),
                shape = RoundedCornerShape(40.dp)
            )
    ) {
        Text(
            modifier = modifier
                .padding(horizontal = 8.dp),
            text = text,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal
            ),
            color = Color(0xFF660EC8)
        )
    }
}

@Composable
fun ChipsList() {
    val chipsList = listOf(
        MyFilterChip(modifier = Modifier, text = "Java"),
        MyFilterChip(modifier = Modifier, text = "Kotlin"),
        MyFilterChip(modifier = Modifier, text = "Android")
    )
}