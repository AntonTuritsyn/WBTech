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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun MyFilterChip(modifier: Modifier, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                Color(0xFFF5ECFF),
                shape = RoundedCornerShape(40.dp)
            )
    ) {
        SomeText(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            text = text,
            fontFamily = SfProDisplay,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = Color(0xFF660EC8)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prew() {
    MyFilterChip(modifier = Modifier, text = "KDHF")
}