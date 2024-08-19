package com.turitsynanton.android.wbtech.uinew.items

import android.widget.Switch
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.ui.theme.NeutralWhite

@Composable
internal fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shadowColor = if (checked) Color(0xFF7B0CBF) else Color(0xFFE1E1E1)
    val trackColor = if (checked) Color(0xFF9A10F0) else Color(0xFFEFEFEF)

    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onCheckedChange(!checked)
            }
            .size(48.dp, 24.dp)
    ) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(trackColor)
        )
        // Thumb
        Box(
            modifier = Modifier
                .align(if (checked) Alignment.CenterEnd else Alignment.CenterStart)
                .padding(horizontal = 1.dp)
                .size(22.dp)
                .border(width = 0.7.dp, color = shadowColor, shape = CircleShape)
                .shadow(elevation = 2.dp, shape = CircleShape, ambientColor = Color(0x0F000000))
                .background(NeutralWhite, CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TogglePreview() {
    var isChecked by remember { mutableStateOf(false) }
    CustomSwitch(checked = isChecked, onCheckedChange = {isChecked = it})
}