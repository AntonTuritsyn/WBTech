package com.turitsynanton.android.wbtech.uinew.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.turitsynanton.android.wbtech.ui.theme.NeutralWhite

internal enum class SubscribeButtonStyle(
    val crossColor: Color,
    val containerColor: Brush,
    val icon: ImageVector
) {
    Done(
        crossColor = NeutralWhite,
        containerColor = Brush.linearGradient(
            listOf(
                Color(0xFF9A10F0),
                Color(0xFF9A10F0)
            )
        ),
        icon = Icons.Rounded.Done
    ),
    Default(
        crossColor = Color(0xFF9A10F0),
        containerColor = Brush.linearGradient(
            listOf(
                Color(0xFFFEF1FB),
                Color(0xFFFDF1FC),
                Color(0xFFFCF0FC),
                Color(0xFFFBF0FD),
                Color(0xFFF9EFFD),
                Color(0xFFF8EEFE),
                Color(0xFFF6EEFE),
                Color(0xFFF4EDFF)
            )
        ),
        icon = Icons.Rounded.Add
    )
}