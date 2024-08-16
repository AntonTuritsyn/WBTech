package com.turitsynanton.android.wbtech.uinew.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal enum class TagsStyle(
    val textColor: Color,
    val containerColor: Color,
    val clickable: Boolean,
    val fontSize: TextUnit,
    val verticalPaddings: Dp,
    val horizontalPaddings: Dp
) {
    Unselected(
        textColor = Color(0xFF9A10F0),
        containerColor = Color(0xFFF6F6FA),
        clickable = true,
        fontSize = 16.sp,
        verticalPaddings = 8.dp,
        horizontalPaddings = 8.dp
    ),
    Selected(
        textColor = Color(0xFFF6F6FA),
        containerColor = Color(0xFF9A10F0),
        clickable = true,
        fontSize = 16.sp,
        verticalPaddings = 8.dp,
        horizontalPaddings = 8.dp
    ),
    Minimize(
        textColor = Color(0xFF9A10F0),
        containerColor = Color(0xFFF6F6FA),
        clickable = false,
        fontSize = 14.sp,
        verticalPaddings = 2.dp,
        horizontalPaddings = 6.dp
    ),
    UnselectedBig(
        textColor = Color(0xFF9A10F0),
        containerColor = Color(0xFFF6F6FA),
        clickable = true,
        fontSize = 22.sp,
        verticalPaddings = 10.dp,
        horizontalPaddings = 12.dp
    ),
    SelectedBig(
        textColor = Color(0xFFF6F6FA),
        containerColor = Color(0xFF9A10F0),
        clickable = true,
        fontSize = 22.sp,
        verticalPaddings = 10.dp,
        horizontalPaddings = 12.dp
    )
}