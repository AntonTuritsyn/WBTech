package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
internal fun MorePeople(
    modifier: Modifier,
    numberOfPeople: Int
) {
    SimpleTextField(
        modifier = modifier
            .clip(shape = CircleShape)
            .border(width = 2.dp, color = Color.White, shape = CircleShape)
            .background(
                color = Color(0xFFF6F6FA)
            )
            .clickable(
                enabled = false
            ) {

            }
            .size(48.dp)
            .wrapContentSize(align = Alignment.Center),
        text = "+$numberOfPeople",
        fontFamily = SfProDisplay,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        color = Color(0xFF9A10F0)
    )
}

@Preview(showBackground = true)
@Composable
private fun MorePeoplePreview() {
    MorePeople(modifier = Modifier, numberOfPeople = 48)
}