package com.turitsynanton.android.wbtech.uinew.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.turitsynanton.android.wbtech.ui.theme.NeutralWhite

internal enum class ButtonStyle(
    var textColor: Color,
    var gradient: Brush,
    var clickable: Boolean,
    var loading: Boolean = false
) {
    Enable(
        textColor = NeutralWhite,
        gradient = Brush.linearGradient(
            listOf(
                Color(0xFFED3CCA),
                Color(0xFFDF34D2),
                Color(0xFFD02BD9),
                Color(0xFFBF22E1),
                Color(0xFFAE1AE8),
                Color(0xFF9A10F0),
                Color(0xFF8306F7),
                Color(0xFF6600FF),
            )
        ),
        clickable = true
    ),
    Disable(
        textColor = Color(0xFF9797AF),
        gradient = Brush.linearGradient(
            listOf(
                Color(0xFFF6F6FA),
                Color(0xFFF6F6FA),
            )
        ),
        clickable = false
    ),
    Loading(
        textColor = NeutralWhite,
        gradient = Brush.linearGradient(
            listOf(
                Color(0xFFED3CCA),
                Color(0xFFDF34D2),
                Color(0xFFD02BD9),
                Color(0xFFBF22E1),
                Color(0xFFAE1AE8),
                Color(0xFF9A10F0),
                Color(0xFF8306F7),
                Color(0xFF6600FF),
            )
        ),
        clickable = false,
        loading = true
    ),
    Secondary(
        textColor = Color(0xFF9A10F0),
        gradient = Brush.linearGradient(
            listOf(
                Color(0xFFFEF1FB),
                Color(0xFFFDF1FC),
                Color(0xFFFCF0FC),
                Color(0xFFFBF0FD),
                Color(0xFFF9EFFD),
                Color(0xFFF8EEFE),
                Color(0xFFF6EEFE),
                Color(0xFFF4EDFF),
            )
        ),
        clickable = true
    )
}