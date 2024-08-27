package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
internal fun SimpleTextField(
    modifier: Modifier,
    text: String,
    fontFamily: FontFamily = SfProDisplay,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontStyle: FontStyle = FontStyle.Normal,
    color: Color,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        letterSpacing = 0.5.sp,
        modifier = modifier,
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        color = color,
        textAlign = textAlign,
//        style = lineHeight,
        maxLines = maxLines,
        overflow = overflow,
        lineHeight = lineHeight
    )
}