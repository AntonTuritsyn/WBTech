package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun SomeText(
    modifier: Modifier,
    text: String,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontStyle: FontStyle,
    color: Color
) {
    Text(
        letterSpacing = 0.5.sp,
        modifier = modifier,
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun SomeTextPreview() {
    Column {
        Text(
            text = "Мои встречи",
            fontFamily = SfProDisplay,
            fontWeight = FontWeight.Black,
            fontStyle = FontStyle.Normal,
            color = Color.Unspecified
            /*style = TextStyle(
                fontSize = size,
                fontWeight = style
            ),*/

        )
    }

}