package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.PhoneVisualTransformation

@Composable
internal fun PhoneField(
    modifier: Modifier, number: String, onPhoneEntered: (String) -> Unit
) {
    BasicTextField(
        value = number,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { newInput ->
            val filteredInput = newInput.filter { it.isDigit() }
            onPhoneEntered(filteredInput)
        },
        visualTransformation = PhoneVisualTransformation(),
        modifier = modifier
            .fillMaxWidth()
            .background(
                NeutralSecondaryBG,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 16.dp, horizontal = 12.dp),
        textStyle = TextStyle(
            fontFamily = SfProDisplay,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .padding(start = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (number.isEmpty())
                    SimpleTextField(
                        modifier = Modifier,
                        text = stringResource(R.string.phone_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFADB5BD)
                    )
                innerTextField()
            }
        }
    )
}