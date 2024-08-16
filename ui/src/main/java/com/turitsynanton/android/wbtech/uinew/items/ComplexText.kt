package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.utils.TextFieldStyle

@Composable
internal fun ComplexTextField(
    hint: String,
    textFieldStyle: TextFieldStyle
) {

//    TODO убрать в VM!!!
    var query by rememberSaveable { mutableStateOf("") }

    BasicTextField(
        modifier = Modifier
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
            query = it
            /*if (it.length > 1) {
                onNameEntered(user.copy(name = it))
            }*/
        },
        enabled = true,
        textStyle = TextStyle(
            color = Color(0xFF000000),
            fontSize = 18.sp,
            fontFamily = SfProDisplay,
            fontWeight = FontWeight.Normal,
        ),
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

@Preview(showBackground = true)
@Composable
private fun ComplexTextFieldPreview() {
    ComplexTextField(hint = "Имя и фамилия", textFieldStyle = TextFieldStyle.Error)
}

