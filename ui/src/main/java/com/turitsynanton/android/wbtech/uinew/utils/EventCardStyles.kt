package com.turitsynanton.android.wbtech.uinew.utils

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal enum class EventCardStyles(
    val height: Dp,
    val width: Dp,
    val textHeight: TextUnit,
    val fontWeight: FontWeight
) {
    Full(height = 180.dp, width = 320.dp, textHeight = 34.sp, fontWeight = FontWeight.Bold),
    Large(height = 180.dp, width = 320.dp, textHeight = 24.sp, fontWeight = FontWeight.Bold),
    Medium(height = 148.dp, width = 212.dp, textHeight = 18.sp, fontWeight = FontWeight.SemiBold),
    Small(height = 104.dp, width = 104.dp, textHeight = 14.sp, fontWeight = FontWeight.SemiBold)
}