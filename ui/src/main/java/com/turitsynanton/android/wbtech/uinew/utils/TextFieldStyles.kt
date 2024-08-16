package com.turitsynanton.android.wbtech.uinew.utils

import androidx.compose.ui.graphics.Color

internal enum class TextFieldStyle(
    val containerColor: Color,
    val border: Boolean
) {
    Focused(
        containerColor = Color(0xFFF6F6FA),
        border = true
    ),
    Filled(
        containerColor = Color(0xFFF6F6FA),
        border = false
    ),
    Error(
        containerColor = Color(0xFFFDE7ED),
        border = false
    )
}