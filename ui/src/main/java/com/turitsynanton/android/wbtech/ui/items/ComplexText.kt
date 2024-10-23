package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.utils.TextFieldStyle

@Composable
internal fun ComplexTextField(
    modifier: Modifier = Modifier,
    hint: String,
    query: String,
    onQueryChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine : Boolean = true,
    textFieldStyle: TextFieldStyle
) {
    BasicTextField(
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = textFieldStyle.containerColor)
            .border(
                width = 1.dp,
                color = if (textFieldStyle.border) BrandColorDefault else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 20.dp),
        value = query,
        onValueChange = {
            onQueryChanged(it)
        },
        singleLine = singleLine,
        enabled = true,
        textStyle = TextStyle(
            color = Color(0xFF000000),
            fontSize = 18.sp,
            fontFamily = SfProDisplay,
            fontWeight = FontWeight.Normal,
        ),
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Decoration(query = query, hint = hint)
            innerTextField()
        }
    )
}

@Composable
private fun Decoration(
    query: String,
    hint: String
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        if (query.isEmpty()) {
            Text(
                text = hint,
                color = NeutralDisabled,
                fontSize = 18.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight.Normal
            )
        }
    }
}