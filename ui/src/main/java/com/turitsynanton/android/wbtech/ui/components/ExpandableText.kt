package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
internal fun ExpandableText(
    modifier: Modifier,
    text: String,
    maxLines: Int,
    maxLinesMinimise: Int,
    expanded: Boolean,
    onClick: () -> Unit
) {
    SomeText(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }) {
                onClick()
            },
        text = text,
        fontFamily = SfProDisplay,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        color = NeutralWeak,
        maxLines = if (expanded) maxLines else maxLinesMinimise
    )
}