package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.SubdcribeButtonStyle

@Composable
internal fun GradientButton(
    modifier: Modifier,
    text: String,
    enable: ButtonStyle,
    onClick: () -> Unit
) {
    if (enable.loading) {
        CircularProgressIndicator(
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = enable.gradient
                )
                .padding(16.dp),
            color = enable.textColor
        )
    } else {
        SimpleTextField(
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = enable.gradient
                )
                .clickable(
                    enabled = enable.clickable
                ) {
                    onClick()
                }
                .padding(16.dp),
            text = text,
            fontFamily = SfProDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = enable.textColor
        )
    }
}

@Composable
internal fun SubdcribeButton(
    modifier: Modifier,
    style: SubdcribeButtonStyle,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(12.dp))
        .background(
            brush = style.containerColor
        )
        .clickable {
            onClick()
        }
        .padding(8.dp)
    ) {
        Icon(imageVector = style.icon, contentDescription = "", tint = style.crossColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun GradientButtonPreview() {
    Column {
        GradientButton(
            modifier = Modifier,
            text = "Оплатить",
            enable = ButtonStyle.Secondary
        ) {
        }
        Spacer(modifier = Modifier.padding(18.dp))
        Row {
            SubdcribeButton(modifier = Modifier, style = SubdcribeButtonStyle.Done) {
            }
            Spacer(modifier = Modifier.padding(18.dp))
            SubdcribeButton(modifier = Modifier, style = SubdcribeButtonStyle.Default) {
            }
        }
    }
}